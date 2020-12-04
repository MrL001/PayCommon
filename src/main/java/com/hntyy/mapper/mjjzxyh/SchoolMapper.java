package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.test.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学校表
 */
@Mapper
public interface SchoolMapper {

    List<SchoolEntity> findAll();

}
