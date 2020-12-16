package com.hntyy;

import com.hntyy.common.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.hntyy.mapper")  //使用MapperScan批量扫描所有的Mapper接口；
@Import(SpringUtil.class)
public class ThirdProjectApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ThirdProjectApplication.class, args);
    }

}
