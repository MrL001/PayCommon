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
    @Excel(name = "档口名称", width = 25,orderNum = "1")
    private String shopName;

    /**
     * 总价统计 默认值：0 查
     */
    @Excel(name = "总金额", width = 25,orderNum = "3")
    private BigDecimal totalPrices = BigDecimal.ZERO;

    /**
     * 退款总金额 默认值：
     */
    @Excel(name = "退款金额", width = 25,orderNum = "4")
    private BigDecimal refundTotalPrices = BigDecimal.ZERO;

    /**
     * 有效总金额 默认值：0  总金额-退款金额
     */
    @Excel(name = "有效总金额", width = 25,orderNum = "2")
    private BigDecimal validTotalPrices = BigDecimal.ZERO;

    /**
     * 总订单量
     */
    private int orderNums;

    /**
     * 退款总订单量
     */
    private int refundOrderNums;

    /**
     * 有效总订单量  总订单量-退款总订单量
     */
    @Excel(name = "订单量", width = 25,orderNum = "5")
    private int validOrderNums;

    /**
     * 总配送费
     */
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    /**
     * 退款总配送费
     */
    private BigDecimal refundDeliveryFee = BigDecimal.ZERO;

    /**
     * 有效总配送费   总配送费-退款总配送费
     */
    @Excel(name = "配送金额", width = 25,orderNum = "6")
    private BigDecimal validDeliveryFee = BigDecimal.ZERO;

    /**
     * 店铺收入  有效总金额-有效总配送费
     */
    @Excel(name = "店铺收入", width = 25,orderNum = "7")
    private BigDecimal shopIncome = BigDecimal.ZERO;

}
