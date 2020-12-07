package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.CanteenEntity;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanteenService {

    /**
     * 通过学校id查询食堂信息
     * @param schoolId
     * @return
     */
    List<CanteenEntity> findCanteenBySchoolId(Long schoolId);

    /**
     * 通过id查询食堂信息
     * @param canteenId
     * @return
     */
    CanteenEntity findCanteenById(Long canteenId);

}
