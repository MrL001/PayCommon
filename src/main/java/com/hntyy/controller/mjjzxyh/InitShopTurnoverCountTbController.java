package com.hntyy.controller.mjjzxyh;

import com.hntyy.common.DateUtil;
import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.ShopEntity;
import com.hntyy.service.mjjzxyh.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 初始化店铺营业额统计表的数据
 */
@Slf4j
@RestController
@RequestMapping("/turnover")
public class InitShopTurnoverCountTbController {

    @Autowired
    private CommonService commonService;

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
        for (String date:dates){
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

        }
        log.info("success");
        return "success";
    }

}
