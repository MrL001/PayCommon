package com.hntyy.service.test;

import com.hntyy.entity.test.User;

import java.util.List;

public interface UserService {

    List<User> findAll(User user);

    int getCount(User user);

}
