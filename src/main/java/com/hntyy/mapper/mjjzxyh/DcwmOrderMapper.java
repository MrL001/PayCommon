package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.test.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DcwmOrderMapper {

    List<User> findAll();

}
