package com.hntyy.scheduled;

import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.service.mjjzxyh.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 营业额统计定时任务
 */
@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TurnoverCountScheduleTask {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopTurnoverCountService shopTurnoverCountService;

    //3.添加定时任务 每天晚上一点执行
    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        DcwmOrderQuery dcwmOrderQuery = new DcwmOrderQuery();
        // 获取所有店铺
        List<ShopEntity> shops = shopService.findShopAll();
        // 店铺ids
        List<Long> shopIds = new ArrayList<>();
        shops.stream().forEach(shop -> shopIds.add(shop.getShopId()));
        // 通过店铺ids & 日期 查询订单信息
        dcwmOrderQuery.setShopIds(shopIds);
        // 前一天
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000));
        dcwmOrderQuery.setQueryDate(date);
        dcwmOrderQuery.setQueryEndDate(date);
        // 每次设置配送Null
        dcwmOrderQuery.setDeliveryMode(null);

        // 获取值并组装
        List<DcwmOrderRusult> orders = orderService.findOrderByShopIdsAndDate(dcwmOrderQuery);
        List<DcwmOrderRusult> refundOrders = orderService.findRefundOrderByShopIdsAndDate(dcwmOrderQuery);

        List<DcwmOrderRusult> result = commonService.assemTurnoverCountData(orders, shops, refundOrders, dcwmOrderQuery);

        // 将result结果插入shop_turnover_count_tb表中
        result.stream().forEach(r -> {
            ShopEntity shop = shopService.findShopByShopId(r.getShopId());
            r.setSchoolId(shop.getSchoolId());
            r.setCanteenId(shop.getCanteenId());
            r.setCountDate(date);
        });
        shopTurnoverCountService.insertBatch(result);
        log.info("success");
    }

}
