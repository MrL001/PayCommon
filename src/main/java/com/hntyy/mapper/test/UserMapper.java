package com.hntyy.mapper.test;

import com.hntyy.entity.test.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll(User user);

}
