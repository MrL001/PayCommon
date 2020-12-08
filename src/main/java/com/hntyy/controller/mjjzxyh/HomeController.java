package com.hntyy.controller.mjjzxyh;

import com.hntyy.entity.test.User;
import com.hntyy.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User测试类（用于测试Springboot+thymeleaf+bootstrap+mybatis框架流程）
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * 首页地址
     * @param mv
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView findAll(ModelAndView mv){
        mv.setViewName("/index");
        List<User> all = userService.findAll(new User());
        mv.addObject("all",all);
        return mv;
    }

}
