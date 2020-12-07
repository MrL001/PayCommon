package com.hntyy.service.test;

import com.hntyy.entity.test.User;
import com.hntyy.mapper.test.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(User user) {
        return userMapper.findAll(user);
    }

}
