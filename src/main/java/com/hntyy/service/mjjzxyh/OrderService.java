package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;

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

    /**
     * 查询总的数据 不分组
     * @return
     */
    DcwmOrderRusult findOrderByShopIdsAndDateNotGroup(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 查询退款数据 不分组
     * @return
     */
    DcwmOrderRusult findRefundOrderByShopIdsAndDateNotGroup(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 商品订单列表导出
     * @param dcwmOrderQuery
     * @return
     */
    List<ShopOrderEntity> exportShopOrderList(DcwmOrderQuery dcwmOrderQuery);

}
