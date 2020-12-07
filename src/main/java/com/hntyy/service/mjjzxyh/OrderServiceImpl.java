package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.mapper.mjjzxyh.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<DcwmOrderRusult> findOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery) {
        return orderMapper.findOrderByShopIdsAndDate(dcwmOrderQuery);
    }

}
