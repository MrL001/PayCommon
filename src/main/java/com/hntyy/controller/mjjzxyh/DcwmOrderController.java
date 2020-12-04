package com.hntyy.controller.mjjzxyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.entity.test.User;
import com.hntyy.service.mjjzxyh.CanteenService;
import com.hntyy.service.mjjzxyh.OrderService;
import com.hntyy.service.mjjzxyh.SchoolService;
import com.hntyy.service.mjjzxyh.ShopService;
import com.hntyy.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

/**
 * 校源汇-点餐外卖订单 Controller
 */
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
        PageHelper<User> pageHelper = new PageHelper();

        // 店铺信息: 食堂id
        List<ShopEntity> shops = shopService.findShopByCanteenId(dcwmOrderQuery.getCanteenId());
        // 店铺ids
        List<Long> shopIds = new ArrayList<>();
        shops.stream().forEach(shop -> shopIds.add(shop.getShopId()));
        // 店铺为空直接返回
        if (CollectionUtils.isEmpty(shopIds)){
            pageHelper.setTotal(0);
            pageHelper.setRows(new ArrayList<>());
            return JSON.toJSONString(pageHelper);
        }

        // 通过店铺ids & 日期 查询订单信息
        List<OrderEntity> orders = orderService.findOrderByShopIdsAndDate(shopIds, dcwmOrderQuery.getQueryDate());

        // 统计总记录数
//        Integer total = userService.getCount(user);
//        pageHelper.setTotal(total);
        // 查询当前页实体对象
//        List<User> list = userService.findAll(user);
//        pageHelper.setRows(list);

        return JSON.toJSONString(pageHelper);
    }

    @RequestMapping("/findCanteenBySchoolId")
    @ResponseBody
    public String findCanteenBySchoolId(Long schoolId) {
        // 查询食堂
        List<CanteenEntity> canteens = canteenService.findCanteenBySchoolId(schoolId);
        JSONObject ob = new JSONObject();
        ob.put("canteens",canteens);
        return ob.toString();
    }



}
