package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.OrderEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.mapper.mjjzxyh.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderEntity> findOrderByShopIdsAndDate(List<Long> shopIds, String date) {
        return orderMapper.findOrderByShopIdsAndDate(shopIds,date);
    }

}
