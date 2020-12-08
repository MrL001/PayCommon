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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 根据骑手维度统计订单
 */
@Slf4j
@RestController
@RequestMapping("/riderOrder")
public class RiderOrderController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    // 全局变量，用于导出获取，减少查询次数
    private List<RiderOrderRusult> riderOrderRusults = new ArrayList<>();

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/mjjzxyh/riderOrderList");
        return mv;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(RiderOrderQuery riderOrderQuery) {
        // 清除数据
        riderOrderRusults = new ArrayList<>();

        PageHelper<RiderOrderRusult> pageHelper = new PageHelper();
        // 查骑手订单
        riderOrderRusults = orderService.findOrderByRiderAccount(riderOrderQuery);
        int count = orderService.findOrderCountByRiderAccount(riderOrderQuery);
        pageHelper.setTotal(count);
        pageHelper.setRows(riderOrderRusults);
        return JSON.toJSONString(pageHelper);
    }

    @RequestMapping("/exportRiderOrder")
    public void exportRiderOrder(HttpServletResponse response){
        try {
            Date date = new Date();
            String strDateFormat = "yyyyMMdd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            // 设置响应输出的头类型及下载文件的默认名称
            String fileName = "骑手订单量统计表_"+ dateFormat.format(date) +".xls";
            String fileNames = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileNames);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), RiderOrderRusult.class, riderOrderRusults);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("请求 exportDcwmOrder 异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
