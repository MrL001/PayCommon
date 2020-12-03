package com.hntyy.controller.test;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询列表
     * @param mv
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv){
        mv.setViewName("/test/userList");
        List<User> all = userService.findAll(new User());
        mv.addObject("all",all);
        return mv;
    }

}
