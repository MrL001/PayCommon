package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.mapper.mjjzxyh.SchoolMapper;
import com.hntyy.mapper.mjjzxyh.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<ShopEntity> findShopByCanteenIdPage(DcwmOrderQuery dcwmOrderQuery) {
        return shopMapper.findShopByCanteenIdPage(dcwmOrderQuery);
    }

    @Override
    public List<ShopEntity> findShopByCanteenId(DcwmOrderQuery dcwmOrderQuery) {
        return shopMapper.findShopByCanteenId(dcwmOrderQuery);
    }

    @Override
    public int findShopCountByCanteenId(DcwmOrderQuery dcwmOrderQuery) {
        return shopMapper.findShopCountByCanteenId(dcwmOrderQuery);
    }

}
