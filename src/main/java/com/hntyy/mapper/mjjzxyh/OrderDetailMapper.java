package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailEntity> findOrderDetailByOrderId(@Param("orderId")Long orderId);

}
