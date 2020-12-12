package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private OrderService orderService;

    @Override
    public List<DcwmOrderRusult> assemTurnoverCountData(List<DcwmOrderRusult> orders, List<ShopEntity> shops, List<DcwmOrderRusult> refundOrders, DcwmOrderQuery dcwmOrderQuery) {
        List<DcwmOrderRusult> result = new ArrayList<>();
        boolean empty = CollectionUtils.isEmpty(orders);
        shops.stream().forEach(shop -> {  // 循环店铺
            DcwmOrderRusult dcwmOrderRusult = new DcwmOrderRusult();
            dcwmOrderRusult.setShopId(shop.getShopId());
            dcwmOrderRusult.setShopName(shop.getName());
            if (!empty){  // 订单非空
                orders.stream().forEach(order -> {
                    // 可以使用.longValue()比较 long 类型。
                    if (order.getShopId().longValue() == shop.getShopId().longValue()){
                        // 填充
                        dcwmOrderRusult.setTotalPrices(order.getTotalPrices());  //总金额
                        dcwmOrderRusult.setOrderNums(order.getOrderNums());  //总订单量
                        dcwmOrderRusult.setDeliveryFee(order.getDeliveryFee());  //总配送费
                        dcwmOrderRusult.setCouponMoney(order.getCouponMoney());  //总优惠券使用金额
                    }
                });
            }
            result.add(dcwmOrderRusult);
        });
        if (!empty){
            result.stream().forEach(r -> {
                // 设置中间变量，判断是否能查到退款数据
                AtomicBoolean isExist = new AtomicBoolean(false);
                refundOrders.stream().forEach(refundOrder -> {
                    if (refundOrder.getShopId().longValue() == r.getShopId().longValue()) {
                        isExist.set(true);
                        // 填充
                        r.setValidTotalPrices(r.getTotalPrices().subtract(refundOrder.getRefundTotalPrices()));  // 有效总金额
                        r.setValidOrderNums(r.getOrderNums()-refundOrder.getRefundOrderNums()); // 有效订单量
                        r.setValidDeliveryFee(r.getDeliveryFee().subtract(refundOrder.getRefundDeliveryFee()));  // 有效配送费
                        r.setRefundTotalPrices(refundOrder.getRefundTotalPrices());  // 退款金额
                        r.setRefundOrderNums(refundOrder.getRefundOrderNums());
                        r.setShopIncome(r.getValidTotalPrices().subtract(r.getValidDeliveryFee()));  // 店铺收入
                        r.setValidCouponMoney(r.getCouponMoney().subtract(refundOrder.getRefundCouponMoney()));
                    }
                });
                // 无退款设置
                if (isExist.get() == false){
                    r.setValidTotalPrices(r.getTotalPrices());  // 有效总金额
                    r.setValidOrderNums(r.getOrderNums()); // 有效订单量
                    r.setValidDeliveryFee(r.getDeliveryFee());  // 有效配送费
                    r.setShopIncome(r.getValidTotalPrices().subtract(r.getValidDeliveryFee()));  // 店铺收入
                    r.setValidCouponMoney(r.getCouponMoney());
                }
            });
        }

        /**
         * 设置配送订单量
         */
        dcwmOrderQuery.setDeliveryMode(1);
        // 总配送订单
        List<DcwmOrderRusult> deliveOrders = orderService.findOrderByShopIdsAndDate(dcwmOrderQuery);
        // 退款总配送订单
        List<DcwmOrderRusult> refundDeliveryOrders = orderService.findRefundOrderByShopIdsAndDate(dcwmOrderQuery);
        boolean emptyDelive = CollectionUtils.isEmpty(deliveOrders);
        boolean refundDelivery = CollectionUtils.isEmpty(refundDeliveryOrders);
        // 用于统计配送订单
        List<DcwmOrderRusult> deliveList = new ArrayList<>();
        if (!emptyDelive){
            if (refundDelivery){
                deliveOrders.stream().forEach(d -> {
                    result.stream().forEach(order -> {
                        if (order.getShopId().longValue() == d.getShopId().longValue()){
                            order.setDeliveryOrderNums(d.getOrderNums());
                        }
                    });
                });
            } else {
                deliveOrders.stream().forEach(d -> {
                    DcwmOrderRusult dcwmOrderRusult = new DcwmOrderRusult();
                    dcwmOrderRusult.setShopId(d.getShopId());
                    dcwmOrderRusult.setDeliveryOrderNums(d.getOrderNums());
                    deliveList.add(dcwmOrderRusult);
                    refundDeliveryOrders.stream().forEach(r -> {
                        if (r.getShopId().longValue() == d.getShopId().longValue()){
                            dcwmOrderRusult.setDeliveryOrderNums(d.getOrderNums()-r.getRefundOrderNums());
                        }
                    });
                });
            }
        }
        // 如果退款不为空赋值
        if (!refundDelivery){
            result.stream().forEach(order -> {
                deliveList.stream().forEach(d -> {
                    if (order.getShopId().longValue() == d.getShopId().longValue()){
                        order.setDeliveryOrderNums(d.getDeliveryOrderNums());
                    }
                });
            });
        }
        return result;
    }
}
