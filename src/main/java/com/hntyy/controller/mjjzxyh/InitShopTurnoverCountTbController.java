package com.hntyy.controller.mjjzxyh;

import com.hntyy.common.DateUtil;
import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.service.mjjzxyh.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 初始化店铺营业额统计表的数据
 */
@Slf4j
@RestController
@RequestMapping("/turnover")
public class InitShopTurnoverCountTbController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ShopTurnoverCountService shopTurnoverCountService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/init")
    @ResponseBody
    public String init() {
        /**
         * 当前日期前一天统计 - 2020.8.24(第一批数据)
         * 照店铺 & 日期执行批量循环操作
         */
        DcwmOrderQuery dcwmOrderQuery = new DcwmOrderQuery();
        // 获取所有店铺
        List<ShopEntity> shops = shopService.findShopAll();
        // 店铺ids
        List<Long> shopIds = new ArrayList<>();
        shops.stream().forEach(shop -> shopIds.add(shop.getShopId()));
        // 通过店铺ids & 日期 查询订单信息
        dcwmOrderQuery.setShopIds(shopIds);
        // 获取日期数组
        List<String> dates = DateUtil.addDates("2020-08-24", new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000)));
//        List<String> dates = DateUtil.addDates("2020-11-01", "2020-11-01");
        for (String date:dates){
            dcwmOrderQuery.setQueryDate(date);
            dcwmOrderQuery.setQueryEndDate(date);
            // 每次设置配送Null
            dcwmOrderQuery.setDeliveryMode(null);

            // 获取值并组装
            List<DcwmOrderRusult> orders = orderService.findOrderByShopIdsAndDate(dcwmOrderQuery);
            List<DcwmOrderRusult> refundOrders = orderService.findRefundOrderByShopIdsAndDate(dcwmOrderQuery);

            List<DcwmOrderRusult> result = assemData(orders, shops, refundOrders, dcwmOrderQuery);

            // 将result结果插入shop_turnover_count_tb表中
            result.stream().forEach(r -> {
                ShopEntity shop = shopService.findShopByShopId(r.getShopId());
                r.setSchoolId(shop.getSchoolId());
                r.setCanteenId(shop.getCanteenId());
                r.setCountDate(date);
            });
            shopTurnoverCountService.insertBatch(result);

        }
        log.info("success");
        return "success";
    }


    /**
     * 组装数据：当日订单为空也要显示，所以按店铺维度进行分页
     */
    private List<DcwmOrderRusult> assemData(List<DcwmOrderRusult> orders,List<ShopEntity> shops,List<DcwmOrderRusult> refundOrders,DcwmOrderQuery dcwmOrderQuery){
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
