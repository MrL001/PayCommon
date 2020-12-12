package com.hntyy.entity.user;

import lombok.Data;

@Data
public class UserEntity {
    private String userName;
    private String password;

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
