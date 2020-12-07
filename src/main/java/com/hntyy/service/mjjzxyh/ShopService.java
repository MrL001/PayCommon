package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import java.util.List;

public interface ShopService {

    /**
     * 食堂id查店铺，分页
     * @param dcwmOrderQuery
     * @return
     */
    List<ShopEntity> findShopByCanteenIdPage(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 食堂id查店铺，不分页用于导出
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
