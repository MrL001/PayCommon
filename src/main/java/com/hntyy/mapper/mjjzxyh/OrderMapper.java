package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 订单表
 */
@Mapper
public interface OrderMapper {

    /**
     * 通过店铺ids & 日期 查询订单信息（总数据）
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
