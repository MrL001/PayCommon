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
     * 通过店铺ids & 日期 查询订单信息
     * @return
     */
    List<DcwmOrderRusult> findOrderByShopIdsAndDate(DcwmOrderQuery dcwmOrderQuery);

}
