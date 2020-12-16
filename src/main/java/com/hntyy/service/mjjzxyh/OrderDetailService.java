package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;

import java.util.List;

public interface OrderDetailService {

    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailEntity> findOrderDetailByOrderId(Long orderId);

}
