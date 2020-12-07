package com.hntyy.controller.mjjzxyh;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.hntyy.common.ExcelStyleUtil;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.service.mjjzxyh.CanteenService;
import com.hntyy.service.mjjzxyh.OrderService;
import com.hntyy.service.mjjzxyh.SchoolService;
import com.hntyy.service.mjjzxyh.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 校源汇-点餐外卖订单 Controller
 */
@Slf4j
@RestController
@RequestMapping("/dcwmOrder")
public class DcwmOrderController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    // 全局变量，用于导出获取，减少查询次数
    private List<DcwmOrderRusult> result = new ArrayList<>();
    private Long schoolId;
    private Long canteenId;

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
        // 清楚数据
        result = new ArrayList<>();
        schoolId = dcwmOrderQuery.getSchoolId();
        canteenId = dcwmOrderQuery.getCanteenId();
        PageHelper<DcwmOrderRusult> pageHelper = new PageHelper();

        // 店铺信息: 食堂id
        List<ShopEntity> shops = shopService.findShopByCanteenIdPage(dcwmOrderQuery);
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
        int count = shopService.findShopCountByCanteenId(dcwmOrderQuery);
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

    @RequestMapping("/exportDcwmOrder")
    public void exportDcwmOrder(HttpServletResponse response){
        try {
            // 查询学校&食堂name
            CanteenEntity canteenEntity = canteenService.findCanteenById(canteenId);
            SchoolEntity schoolEntity = schoolService.findSchoolById(schoolId);
            Date date = new Date();
            String strDateFormat = "yyyyMMdd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            // 设置响应输出的头类型及下载文件的默认名称
            ExportParams exportParams = new ExportParams(schoolEntity.getName()+canteenEntity.getName()+"营业额", "营业额统计表", ExcelType.XSSF);
            exportParams.setStyle(ExcelStyleUtil.class);
            String fileName = schoolEntity.getName()+"营业额统计表_"+ dateFormat.format(date) +".xls";

            String fileNames = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileNames);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DcwmOrderRusult.class, result);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("请求 exportDcwmOrder 异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
