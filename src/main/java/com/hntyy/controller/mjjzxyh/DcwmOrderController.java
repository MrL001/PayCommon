package com.hntyy.controller.mjjzxyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.GsonBuilder;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.test.User;
import com.hntyy.service.mjjzxyh.SchoolService;
import com.hntyy.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 校源汇-点餐外卖订单 Controller
 */
@RestController
@RequestMapping("/dcwmOrder")
public class DcwmOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/mjjzxyh/dcwmOrderList");
        // 查询所有学校（用于下拉框）
        List<SchoolEntity> schools = schoolService.findAll();
        mv.addObject("schools",schools);
        return mv;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(User user,HttpServletRequest request) {
        PageHelper<User> pageHelper = new PageHelper();
        // 统计总记录数
        Integer total = userService.getCount(user);
        pageHelper.setTotal(total);

        // 查询当前页实体对象
        List<User> list = userService.findAll(user);
        pageHelper.setRows(list);

        return JSON.toJSONString(pageHelper);
    }


}
