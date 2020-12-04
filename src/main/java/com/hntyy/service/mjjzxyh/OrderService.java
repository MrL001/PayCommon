package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.OrderEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    /**
     * 通过店铺ids & 日期 查询订单信息
     * @param shopIds
     * @param date
     * @return
     */
    List<OrderEntity> findOrderByShopIdsAndDate(List<Long> shopIds, String date);

}
