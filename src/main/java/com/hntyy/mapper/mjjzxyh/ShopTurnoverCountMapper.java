package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopTurnoverCountMapper {

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
