package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.OrderEntity;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    /**
     * 通过店铺ids & 日期 查询订单信息
     * @param dcwmOrderQuery
     * @return
     */
    List<DcwmOrderRusult> findOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery);

}
