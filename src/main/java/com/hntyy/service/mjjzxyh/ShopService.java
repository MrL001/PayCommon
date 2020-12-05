package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {

    /**
     * 食堂id查店铺，分页
     * @param dcwmOrderQuery
     * @return
     */
    List<ShopEntity> findShopByCanteenId(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 食堂id查店铺，分页。总数
     * @param dcwmOrderQuery
     * @return
     */
    int findShopCountByCanteenId(DcwmOrderQuery dcwmOrderQuery);

}
