package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.OrderEntity;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表
 */
@Mapper
public interface OrderMapper {

    /**
     * 通过店铺ids & 日期 查询订单信息
     * @param shopIds
     * @param date
     * @return
     */
    List<OrderEntity> findOrderByShopIdsAndDate(@Param("shopIds") List<Long> shopIds, @Param("date")String date);

}
