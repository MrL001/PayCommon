package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.CanteenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 食堂表
 */
@Mapper
public interface CanteenMapper {

    /**
     * 通过学校id查询食堂信息
     * @param schoolId
     * @return
     */
    List<CanteenEntity> findCanteenBySchoolId(@Param("schoolId") Long schoolId);

}
