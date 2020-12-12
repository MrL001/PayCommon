package com.hntyy.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageHelper<T> {
    // 注意：这两个属性名称不能改变，是定死的
    // 实体类集合
    private List<T> rows = new ArrayList<T>();

    // 数据总条数
    private int total;

    /**
     * 根据需求自定义参数
     */
    // 订单总量
    private int orderSums;

    // 总金额
    private BigDecimal totalPricesSums = BigDecimal.ZERO;

    // 退款金额
    private BigDecimal refundTotalPricesSums = BigDecimal.ZERO;

    // 有效总金额
    private BigDecimal validTotalPricesSums = BigDecimal.ZERO;

    // 退款订单量
    private int refundOrderNumsSums;

    // 有效订单量
    private int validOrderNumsSums;

    // 有效配送订单量
    private int deliveryOrderNumsSums;

    // 有效配送金额
    private BigDecimal validDeliveryFeeSums = BigDecimal.ZERO;

    // 店铺收入
    private BigDecimal shopIncomeSums = BigDecimal.ZERO;

    // 有效优惠券使用金额
    private BigDecimal validCouponMoneySums = BigDecimal.ZERO;

}
