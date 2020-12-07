package com.hntyy.entity.test;

import com.hntyy.entity.Page;
import lombok.Data;

@Data
public class User extends Page {

    // 编号
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

}
