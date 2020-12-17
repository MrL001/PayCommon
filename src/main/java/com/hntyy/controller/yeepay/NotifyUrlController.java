package com.hntyy.controller.yeepay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理回调地址
 */
@Slf4j
@RestController
@RequestMapping("/notifyUrl")
public class NotifyUrlController {

    /**
     * 特约入网回调地址
     */
    @RequestMapping("/registerSaasMerchant")
    public String qualUpload(HttpServletRequest request){
        System.out.println("hello");
        return null;
    }

}
