package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 点餐外卖订单列表 展示Rusult
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DcwmOrderRusult {

    /**
     * 店铺id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long shopId;

    /**
     * 店铺name
     */
    private String shopName;

    /**
     * 总价统计 默认值：0
     */
    private BigDecimal totalPrices = BigDecimal.ZERO;

}
