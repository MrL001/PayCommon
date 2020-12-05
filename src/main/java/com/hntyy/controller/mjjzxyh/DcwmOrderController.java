package com.hntyy.controller.mjjzxyh;

import com.alibaba.fastjson.JSON;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.service.mjjzxyh.CanteenService;
import com.hntyy.service.mjjzxyh.OrderService;
import com.hntyy.service.mjjzxyh.SchoolService;
import com.hntyy.service.mjjzxyh.ShopService;
import com.hntyy.service.test.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

/**
 * 校源汇-点餐外卖订单 Controller
 */
@Slf4j
@RestController
@RequestMapping("/dcwmOrder")
public class DcwmOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/mjjzxyh/dcwmOrderList");
        // 查询所有学校（用于下拉框）
        List<SchoolEntity> schools = schoolService.findAll();
        mv.addObject("schools",schools);
        // 查询食堂（取schools第一个数据作为初始值）
        List<CanteenEntity> canteens = canteenService.findCanteenBySchoolId(schools.get(0).getSchoolId());
        mv.addObject("canteens",canteens);
        return mv;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(DcwmOrderQuery dcwmOrderQuery) {
        PageHelper<DcwmOrderRusult> pageHelper = new PageHelper();

        // 店铺信息: 食堂id
        List<ShopEntity> shops = shopService.findShopByCanteenId(dcwmOrderQuery);
        int count = shopService.findShopCountByCanteenId(dcwmOrderQuery);
        // 店铺ids
        List<Long> shopIds = new ArrayList<>();
        shops.stream().forEach(shop -> shopIds.add(shop.getShopId()));
        // 店铺为空直接返回
        if (CollectionUtils.isEmpty(shopIds)){
            return JSON.toJSONString(pageHelper);
        }
        // 通过店铺ids & 日期 查询订单信息
        dcwmOrderQuery.setShopIds(shopIds);
        List<DcwmOrderRusult> orders = orderService.findOrderByShopIdsAndDate(dcwmOrderQuery);

        /**
         * 组装数据：当日订单为空也要显示，所以按店铺维度进行分页
         */
        boolean empty = CollectionUtils.isEmpty(orders);
        List<DcwmOrderRusult> result = new ArrayList<>();
        shops.stream().forEach(shop -> {  // 循环店铺
            DcwmOrderRusult dcwmOrderRusult = new DcwmOrderRusult();
            dcwmOrderRusult.setShopId(shop.getShopId());
            dcwmOrderRusult.setShopName(shop.getName());
            if (!empty){  // 订单非空
                orders.stream().forEach(order -> {
                    // 可以使用.longValue()比较 long 类型。
                    if (order.getShopId().longValue() == shop.getShopId().longValue()){
                        // 填充金额
                        dcwmOrderRusult.setTotalPrices(order.getTotalPrices());
                    }
                });
            }
            result.add(dcwmOrderRusult);
        });

        // 统计总记录数
        pageHelper.setTotal(count);
        // 当前页实体对象
        pageHelper.setRows(result);

        return JSON.toJSONString(pageHelper);
    }

    @RequestMapping("/findCanteenBySchoolId")
    @ResponseBody
    public List<CanteenEntity> findCanteenBySchoolId(Long schoolId) {
        // 查询食堂
        List<CanteenEntity> canteens = canteenService.findCanteenBySchoolId(schoolId);
        return canteens;
    }



}
