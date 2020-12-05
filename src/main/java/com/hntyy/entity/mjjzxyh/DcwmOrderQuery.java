package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.Page;
import lombok.Data;

import java.util.List;

/**
 * 点餐外卖订单查询类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DcwmOrderQuery extends Page {

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long schoolId;

    /**
     * 店铺ids
     */
    private List<Long> shopIds;

    /**
     * 食堂id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long canteenId;

    /**
     * 查询日期
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private String queryDate;

}
