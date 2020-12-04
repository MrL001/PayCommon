package com.hntyy.entity.mjjzxyh;

import com.hntyy.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 店铺
 */
@Data
public class ShopEntity extends BaseEntity {

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型，1点餐外卖，2购物商城
     */
    private int type;

    /**
     * 背景图
     */
    private String bgicon;

    /**
     * logo
     */
    private String icon;

    /**
     * 销量
     */
    private int saleNumber;

    /**
     * 评论数
     */
    private int commentNumber;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 配送费
     */
    private BigDecimal deliveryFee;

    /**
     * 新客立减
     */
    private BigDecimal newCustomerReduction;

    /**
     * 起送价
     */
    private BigDecimal startPrice;

    /**
     * 全场折扣
     */
    private BigDecimal discount;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 维度
     */
    private BigDecimal latitude;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 营业时间,实例.06:30-07:50,08:50-09:50
     */
    private String businessTime;

    /**
     * 简介
     */
    private String summary;

    /**
     * 接单类型，1默认自动接单,2手动接单
     */
    private int acceptOrderType;

    /**
     * 状态1营业,2休息，3已删除
     */
    private int status;

    /**
     * 学校id,外键
     */
    private Long schoolId;

    /**
     * 食堂id,外键
     */
    private Long canteenId;

    /**
     * 商户id,外键
     */
    private Long accountId;

}
