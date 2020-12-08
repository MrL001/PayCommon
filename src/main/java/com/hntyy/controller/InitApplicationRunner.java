package com.hntyy.controller;

import com.hntyy.common.RedisUtil;
import com.hntyy.entity.mjjzxyh.SensitiveWordsEntity;
import com.hntyy.entity.mjjzxyh.SensitiveWordsQuery;
import com.hntyy.service.mjjzxyh.SensitiveWordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统启动完可以做一些业务操作
 */
@Slf4j
@Component
@Order(1)
public class InitApplicationRunner implements CommandLineRunner {

    @Autowired
    private SensitiveWordsService sensitiveWordsService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... args) throws Exception {
        List<SensitiveWordsEntity> all = sensitiveWordsService.findAll(new SensitiveWordsQuery());
        boolean sensitiveWords = redisUtil.set("sensitiveWords", all);
        log.info("敏感词保存结果："+sensitiveWords);
    }
}