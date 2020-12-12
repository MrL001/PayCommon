package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.mapper.mjjzxyh.ShopTurnoverCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopTurnoverCountServiceImpl implements ShopTurnoverCountService {

    @Autowired
    private ShopTurnoverCountMapper shopTurnoverCountMapper;

    @Override
    public void insertBatch(List<DcwmOrderRusult> list) {
        shopTurnoverCountMapper.insertBatch(list);
    }

    @Override
    public List<DcwmOrderRusult> findTurnoverByCanteenId(DcwmOrderQuery dcwmOrderQuery) {
        return shopTurnoverCountMapper.findTurnoverByCanteenId(dcwmOrderQuery);
    }

    @Override
    public int findTurnoverCountByCanteenId(DcwmOrderQuery dcwmOrderQuery) {
        return shopTurnoverCountMapper.findTurnoverCountByCanteenId(dcwmOrderQuery);
    }

    @Override
    public DcwmOrderRusult findTurnoverByCanteenIdCount(DcwmOrderQuery dcwmOrderQuery) {
        return shopTurnoverCountMapper.findTurnoverByCanteenIdCount(dcwmOrderQuery);
    }
}
