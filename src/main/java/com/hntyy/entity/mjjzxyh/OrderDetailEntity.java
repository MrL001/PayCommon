package com.hntyy.entity.mjjzxyh;

import lombok.Data;

import java.math.BigDecimal;
/**
 * 订单详情
 */
@Data
public class OrderDetailEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String merCateName;

    /**
     * 规格
     */
    private String skuvalue;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

}
