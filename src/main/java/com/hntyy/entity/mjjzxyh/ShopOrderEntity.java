package com.hntyy.entity.mjjzxyh;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品订单
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopOrderEntity extends BaseEntity {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    @Excel(name = "订单号",width = 20, orderNum = "1")
    private String orderNumber;

    /**
     * 下单人
     */
    @Excel(name = "下单人id",width = 20,orderNum = "2")
    private Long accountId;

    // --------------店铺信息-----------
    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 店铺类型  1外卖点餐，2购物商城
     */
    private Integer shopType;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺信息 拼接
     */
    @Excel(name = "店铺信息",width = 30,height = 30,orderNum = "3")
    private String shopInfo;
    // --------------店铺信息 END-----------

    // --------------支付信息---------------
    /**
     * 支付类型  1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额,100线下支付
     */
    private Integer payType;

    /**
     * 包装费
     */
    private BigDecimal boxFee  = BigDecimal.ZERO;;

    /**
     * 配送费
     */
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    /**
     * 新客立减
     */
    private BigDecimal newCustomerReduction  = BigDecimal.ZERO;;

    /**
     * 满减
     */
    private BigDecimal fullReductionMoney  = BigDecimal.ZERO;;

    /**
     * 优惠劵
     */
    private BigDecimal couponMoney  = BigDecimal.ZERO;

    /**
     * 总优惠金额
     */
    private BigDecimal totalReferential  = BigDecimal.ZERO;;

    /**
     * 总费用
     */
    private BigDecimal totalPrice  = BigDecimal.ZERO;;

    /**
     * 支付信息 拼接
     */
    @Excel(name = "支付信息",width = 30,height = 50,orderNum = "4")
    private String payInfo;
    // --------------支付信息 END---------------

    /**
     * 商品信息
     */
    private List<OrderDetailEntity> orderDetailEntityList;

    /**
     * 商品信息 拼接
     */
    @Excel(name = "商品信息",width = 30,height = 50,orderNum = "5")
    private String orderDetail;

    /**
     * 配送方式，1配送，2自取，3堂食
     */
    private Integer deliveryMode;

    @Excel(name = "物流",width = 20,orderNum = "6")
    private String deliveryModeStr;

    // --------------收货信息---------------
    /**
     * 姓名
     */
    private String receiptName;

    /**
     * 性别 1男性，2女性
     */
    private Integer receiptSex;

    /**
     * 手机号
     */
    private String receiptPhone;

    /**
     * 省
     */
    private String receiptProvince;

    /**
     * 市
     */
    private String receiptCity;

    /**
     * 区
     */
    private String receiptArea;

    /**
     * 收货地址
     */
    private String receiptAddress;

    /**
     * 自取时间
     */
    private Date selfTakeDate;

    /**
     * 预留手机号
     */
    private String phone;

    /**
     * 收货信息 拼接
     */
    @Excel(name = "收货信息",width = 30,orderNum = "7")
    private String receiptInfo;
    // --------------收货信息 END---------------

    // --------------时间---------------
    /**
     * 下单
     */
    private Date createDate;

    /**
     * 支付
     */
    private Date paymentDate;

    /**
     * 发货
     */
    private Date updateDate;

    @Excel(name = "时间",width = 20,orderNum = "8")
    private String time;
    // --------------时间 END---------------

    /**
     * 订单状态，2待支付，3待发货,4待收货，5待评价，6已取消，7已删除，8已完成，9退款待审核，10已退款，11已拒绝退款，
     *  31待商家接单，32商家已接单，33商家已出餐，41骑手正在运送，42订单已送达
     */
    private Integer status;

    @Excel(name = "状态",width = 30,orderNum = "10")
    private String statusStr;


}
