package com.hntyy.entity.mjjzxyh;

import com.hntyy.entity.Page;
import lombok.Data;

/**
 * 点餐外卖订单查询类
 */
@Data
public class DcwmOrderQuery extends Page {

    /**
     * 学校id
     */
    private Long schoolId;

    /**
     * 食堂id
     */
    private Long canteenId;

    /**
     * 查询日期
     */
    private String queryDate;

}
