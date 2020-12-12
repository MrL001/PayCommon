package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;

import java.util.List;

public interface CommonService {

    /**
     * 组装营业额统计
     * @param orders
     * @param shops
     * @param refundOrders
     * @param dcwmOrderQuery
     * @return
     */
    List<DcwmOrderRusult> assemTurnoverCountData(List<DcwmOrderRusult> orders, List<ShopEntity> shops, List<DcwmOrderRusult> refundOrders, DcwmOrderQuery dcwmOrderQuery);

}
