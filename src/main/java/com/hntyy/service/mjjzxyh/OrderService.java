package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
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

}
