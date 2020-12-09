package com.hntyy;

import com.hntyy.common.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.hntyy.mapper")  //使用MapperScan批量扫描所有的Mapper接口；
@Import(SpringUtil.class)
public class ThirdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdProjectApplication.class, args);
    }

}
