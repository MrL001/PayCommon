package com.hntyy.controller;

import com.hntyy.entity.user.UserEntity;
import com.hntyy.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping()
public class LoginController {

    @Autowired
   private UserService userService;

    Map<String, String> userMap = new HashMap<>();

    @RequestMapping({"/","/login"})
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute UserEntity user, HttpSession session) {
        UserEntity userEntity = userService.findUserByUserName(user.getUserName());
        if (userEntity == null || !user.getPassword().equals(userEntity.getPassword())){
            return new ModelAndView("login");
        } else {
            //登录成功，session中加入
            session.setAttribute("user",userEntity);
            return new ModelAndView("index");
        }
    }
}
