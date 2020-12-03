package com.hntyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hntyy.mapper")  //使用MapperScan批量扫描所有的Mapper接口；
public class PayCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayCommonApplication.class, args);
    }

}
