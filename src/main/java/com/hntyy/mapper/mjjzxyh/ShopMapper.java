package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺表
 */
@Mapper
public interface ShopMapper {

    /**
     * 通过食堂id店铺信息
     * @param canteenId
     * @return
     */
    List<ShopEntity> findShopByCanteenId(@Param("canteenId") Long canteenId);

}
