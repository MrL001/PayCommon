package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntity extends BaseEntity {

    /**
     * 总优惠金额
     */
    private BigDecimal totalReferential;

    /**
     * 优惠劵金额
     */
    private BigDecimal couponMoney;

    /**
     * 逻辑删除，1未删除，2已删除
     */
    private int deleted;

    /**
     * 订单id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;

    /**
     * 店铺类型，1点餐外卖，2购物商城
     */
    private int shopType;

    /**
     * 店铺订单编号
     */
    private String shopOrderNumber;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 类型，1购买商品，2提现，3退款，4充值，20诚信押金
     */
    private int type;

    /**
     * 方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额,100线下支付
     */
    private int payType;

    /**
     * 配送方式，1配送，2自取
     */
    private int deliveryMode;

    /**
     * 包装费
     */
    private BigDecimal boxFee;

    /**
     * 新客立减
     */
    private BigDecimal newCustomerReduction;

    /**
     * 满减金额
     */
    private BigDecimal fullReductionMoney;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 配送费
     */
    private BigDecimal deliveryFee;

    /**
     * 自取时间
     */
    private Date selfTakeDate;

    /**
     * 预留手机
     */
    private String phone;

    /**
     * 取消原因
     */
    private String cancelReson;

    /**
     * 用户备注
     */
    private String userMark;

    /**
     * 取餐码
     */
    private String codeNumber;

    /**
     * 二维码链接
     */
    private String qrcodeurl;

    /**
     * 未付款自动取消时间，单位秒
     */
    private int nopayAutoCancelTime;

    /**
     * 商家未接单自动取消时间，单位秒
     */
    private int noacceptAutoCancelTime;

    /**
     * 商家备注
     */
    private String merchantMark;

    /**
     * 支付日期
     */
    private Date paymentDate;

    /**
     * 店铺id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long shopId;

    /**
     * 配送人id外键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long riderAccountId;

    /**
     * 商户id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long merchantAccountId;

    /**
     * 推广账户id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long spreadAccountId;

    /**
     * 下单人
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long accountId;

    /**
     * 订单状态，2待支付，3待发货,4待收货，5待评价，6已取消，7已删除，8已完成，9退款待审核，10已退款，11已拒绝退款，
     * 31待商家接单，32商家已接单，33商家已出餐，41骑手正在运送，42订单已送达
     */
    private int status;

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long schoolId;

}
