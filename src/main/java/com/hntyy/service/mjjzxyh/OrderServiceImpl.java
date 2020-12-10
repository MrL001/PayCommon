package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.RiderOrderQuery;
import com.hntyy.entity.mjjzxyh.RiderOrderRusult;
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

    @Override
    public List<DcwmOrderRusult> findRefundOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery) {
        return orderMapper.findRefundOrderByShopIdsAndDate(dcwmOrderQuery);
    }

    @Override
    public List<RiderOrderRusult> findOrderByRiderAccount(RiderOrderQuery riderOrderQuery) {
        return orderMapper.findOrderByRiderAccount(riderOrderQuery);
    }

    @Override
    public int findOrderCountByRiderAccount(RiderOrderQuery riderOrderQuery) {
        return orderMapper.findOrderCountByRiderAccount(riderOrderQuery);
    }

    @Override
    public int findOrderSumsByRiderAccount(RiderOrderQuery riderOrderQuery) {
        return orderMapper.findOrderSumsByRiderAccount(riderOrderQuery);
    }

}
