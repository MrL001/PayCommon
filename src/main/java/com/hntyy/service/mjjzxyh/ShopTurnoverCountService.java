package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;

import java.util.List;

public interface ShopTurnoverCountService {

    /**
     * 批量插入
     */
    void insertBatch(List<DcwmOrderRusult> list);

    /**
     * 通过食堂id查询营业额
     * @return
     */
    List<DcwmOrderRusult> findTurnoverByCanteenId(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 通过食堂id查询营业额总数
     * @param dcwmOrderQuery
     * @return
     */
    int findTurnoverCountByCanteenId(DcwmOrderQuery dcwmOrderQuery);

    /**
     * 通过食堂id查询营业额 总统计
     * @return
     */
    DcwmOrderRusult findTurnoverByCanteenIdCount(DcwmOrderQuery dcwmOrderQuery);

}
