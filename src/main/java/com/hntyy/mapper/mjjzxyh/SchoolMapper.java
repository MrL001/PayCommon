package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SchoolEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学校表
 */
@Mapper
public interface SchoolMapper {

    List<SchoolEntity> findAll();

    SchoolEntity findSchoolById(@Param("schoolId") Long schoolId);
}
