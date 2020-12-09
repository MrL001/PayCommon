package com.hntyy.controller.mjjzxyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hntyy.common.RedisUtil;
import com.hntyy.common.SensitivewordFilter;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.service.mjjzxyh.SensitiveWordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@RequestMapping("/sensitiveWords")
@RestController
public class SensitiveWordsController {

    @Autowired
    private SensitiveWordsService sensitiveWordsService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/mjjzxyh/sensitiveWordsList");
        return mv;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(SensitiveWordsQuery sensitiveWordsQuery) {
        List<SensitiveWordsEntity> all = sensitiveWordsService.findAll(sensitiveWordsQuery);
        int count = sensitiveWordsService.findAllCount(sensitiveWordsQuery);
        PageHelper<SensitiveWordsEntity> pageHelper = new PageHelper();
        pageHelper.setTotal(count);
        pageHelper.setRows(all);
        return JSON.toJSONString(pageHelper);
    }

    @RequestMapping("/saveSensitiveWords")
    @ResponseBody
    public String saveSensitiveWords(HttpServletRequest request) {
        SensitiveWordsEntity sensitiveWordsEntity = new SensitiveWordsEntity();
        String word = request.getParameter("word");
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)){
            sensitiveWordsEntity.setId(Integer.parseInt(id));
        }
        sensitiveWordsEntity.setWord(word);
        try {
            if (id == null || "".equals(id)){
                sensitiveWordsService.save(sensitiveWordsEntity);
            } else {
                sensitiveWordsService.update(sensitiveWordsEntity);
            }
            return "success";
        } catch (Exception e) {
            log.error("保存敏感词失败， sensitiveWordsEntity：{}" , JSON.toJSONString(sensitiveWordsEntity),e);
            return "false";
        }
    }

    @RequestMapping("/deleteSensitiveWords")
    @ResponseBody
    public String deleteSensitiveWords(HttpServletRequest request) {
        String targetID = request.getParameter("targetID");
        int id = Integer.parseInt(targetID);
        try {
            sensitiveWordsService.deleteById(id);
            return "success";
        } catch (Exception e) {
            log.error("删除敏感词失败，id:{} " ,targetID,e);
            return "false";
        }
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(HttpServletRequest request) {
        List<Integer> ids = new ArrayList<>();
        String idStr = request.getParameter("idArray");
        JSONArray id_array = JSON.parseArray(idStr);
        for (int i = 0; i < id_array.size(); i++) {
            int id = (int) id_array.get(i);
            ids.add(id);
        }
        try {
            sensitiveWordsService.batchDelete(ids);
            return "success";
        } catch (Exception e) {
            log.error("批量删除敏感词失败，id:{} " ,JSON.toJSONString(ids),e);
            return "false";
        }
    }

    /**
     * 敏感词匹配测试
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        SensitivewordFilter filter = new SensitivewordFilter(redisUtil,sensitiveWordsService);
        String content = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一迷幻药={MOD}杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + content);
        long beginTime = System.currentTimeMillis();
        Set<String> set = filter.getSensitiveWord(content, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        return "语句中包含敏感词的个数为：" + set.size() + "。包含：" + set;
    }

}
