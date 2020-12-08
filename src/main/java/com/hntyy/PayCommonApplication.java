package com.hntyy;

import com.hntyy.common.RedisUtil;
import com.hntyy.common.SpringUtil;
import com.hntyy.entity.mjjzxyh.SensitiveWordsEntity;
import com.hntyy.service.mjjzxyh.SensitiveWordsService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@MapperScan("com.hntyy.mapper")  //使用MapperScan批量扫描所有的Mapper接口；
@Import(SpringUtil.class)
public class PayCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayCommonApplication.class, args);
    }

}
