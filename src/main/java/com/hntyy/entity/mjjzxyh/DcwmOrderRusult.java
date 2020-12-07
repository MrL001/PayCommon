package com.hntyy.entity.mjjzxyh;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 点餐外卖订单列表 展示Rusult
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DcwmOrderRusult implements Serializable {

    /**
     * 店铺id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    // replace格式为 "替换前的值_替换后的值"
    // @Excel(name = "性别*", replace = {"男_0", "女_1"})
    private Long shopId;

    /**
     * 店铺name
     */
    @Excel(name = "店铺名称", width = 25,orderNum = "1")
    private String shopName;

    /**
     * 总价统计 默认值：0
     */
    @Excel(name = "每日营业额", width = 25,orderNum = "2")
    private BigDecimal totalPrices = BigDecimal.ZERO;

}
