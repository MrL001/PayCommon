package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {

    /**
     * 通过食堂id店铺信息
     * @param canteenId
     * @return
     */
    List<ShopEntity> findShopByCanteenId(Long canteenId);

}
