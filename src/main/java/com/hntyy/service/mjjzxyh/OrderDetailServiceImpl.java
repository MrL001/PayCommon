package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.mapper.mjjzxyh.OrderDetailMapper;
import com.hntyy.mapper.mjjzxyh.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailEntity> findOrderDetailByOrderId(Long orderId) {
        return orderDetailMapper.findOrderDetailByOrderId(orderId);
    }

}
