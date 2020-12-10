package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.RiderOrderQuery;
import com.hntyy.entity.mjjzxyh.RiderOrderRusult;

import java.util.List;

public interface OrderService {

    /**
     * 通过店铺ids & 日期 查询订单信息（总数据）
     * @param dcwmOrderQuery
     * @return
     */
    List<DcwmOrderRusult> findOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 通过店铺ids & 日期 查询订单信息（退款数据）
     * @return
     */
    List<DcwmOrderRusult> findRefundOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 查询骑手订单
     * @return
     */
    List<RiderOrderRusult> findOrderByRiderAccount(RiderOrderQuery riderOrderQuery);

    /**
     * 查询骑手订单
     * @return
     */
    int findOrderCountByRiderAccount(RiderOrderQuery riderOrderQuery);

    /**
     * 查询所有订单量
     * @return
     */
    int findOrderSumsByRiderAccount(RiderOrderQuery riderOrderQuery);

}
