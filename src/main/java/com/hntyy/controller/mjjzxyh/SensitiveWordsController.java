package com.hntyy.controller.mjjzxyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hntyy.common.RedisUtil;
import com.hntyy.common.Result;
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
            // 重新设置redis
            List<SensitiveWordsEntity> allSensitiveWords = sensitiveWordsService.findAllSensitiveWords();
            redisUtil.set("sensitiveWords", allSensitiveWords);
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
            // 重新设置redis
            List<SensitiveWordsEntity> allSensitiveWords = sensitiveWordsService.findAllSensitiveWords();
            redisUtil.set("sensitiveWords", allSensitiveWords);
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
            // 重新设置redis
            List<SensitiveWordsEntity> allSensitiveWords = sensitiveWordsService.findAllSensitiveWords();
            redisUtil.set("sensitiveWords", allSensitiveWords);
            return "success";
        } catch (Exception e) {
            log.error("批量删除敏感词失败，id:{} " ,JSON.toJSONString(ids),e);
            return "false";
        }
    }

    /**
     * 敏感词匹配
     * @return
     */
    @RequestMapping("/sensitiveWordsMatch")
    public Result sensitiveWordsMatch(String content){
        SensitivewordFilter filter = new SensitivewordFilter(redisUtil,sensitiveWordsService);
        Set<String> set = filter.getSensitiveWord(content, 1);
        if (set.size()>0){  //包含敏感字
            return new Result(set,"失败",-1);
        } else {
            return new Result(null,"成功",200);
        }
    }

}
