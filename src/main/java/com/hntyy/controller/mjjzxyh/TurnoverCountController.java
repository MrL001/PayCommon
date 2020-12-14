package com.hntyy.controller.mjjzxyh;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.hntyy.common.UrlUtil;
import com.hntyy.common.ExcelStyleUtil;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.service.mjjzxyh.*;
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

/**
 * 营业额统计
 */
@Slf4j
@RestController
@RequestMapping("/turnoverCount")
public class TurnoverCountController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopTurnoverCountService shopTurnoverCountService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv,DcwmOrderQuery dcwmOrderQuery) {
        mv.setViewName("/mjjzxyh/dcwmOrderList");
        // 查询所有学校（用于下拉框）
        List<SchoolEntity> schools = schoolService.findAll();
        // 学校id加密
        for (SchoolEntity s:schools) {
            s.setSchoolIdStr(UrlUtil.enCryptAndEncode(s.getSchoolId().toString()));
        }
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

        // 统计信息
        List<DcwmOrderRusult> result = shopTurnoverCountService.findTurnoverByCanteenId(dcwmOrderQuery);
        if (CollectionUtils.isEmpty(result)){
            return JSON.toJSONString(pageHelper);
        }

        // 总统计
        DcwmOrderRusult dcwmOrderRusult = shopTurnoverCountService.findTurnoverByCanteenIdCount(dcwmOrderQuery);
        pageHelper.setTotalPricesSums(dcwmOrderRusult.getTotalPrices());
        pageHelper.setRefundTotalPricesSums(dcwmOrderRusult.getRefundTotalPrices());
        pageHelper.setValidTotalPricesSums(dcwmOrderRusult.getValidTotalPrices());
        pageHelper.setRefundOrderNumsSums(dcwmOrderRusult.getRefundOrderNums());
        pageHelper.setValidOrderNumsSums(dcwmOrderRusult.getValidOrderNums());
        pageHelper.setValidDeliveryFeeSums(dcwmOrderRusult.getValidDeliveryFee());
        pageHelper.setValidCouponMoneySums(dcwmOrderRusult.getValidCouponMoney());
        pageHelper.setShopIncomeSums(dcwmOrderRusult.getShopIncome());
        pageHelper.setDeliveryOrderNumsSums(dcwmOrderRusult.getDeliveryOrderNums());

        // 统计总记录数
        int count = shopTurnoverCountService.findTurnoverCountByCanteenId(dcwmOrderQuery);
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
    public void exportDcwmOrder(HttpServletResponse response,DcwmOrderQuery dcwmOrderQuery){
        try {
            // 查询学校&食堂name
            CanteenEntity canteenEntity = canteenService.findCanteenById(dcwmOrderQuery.getCanteenId());
            SchoolEntity schoolEntity = schoolService.findSchoolById(dcwmOrderQuery.getSchoolId());
            Date date = new Date();
            String strDateFormat = "yyyyMMdd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            // 设置响应输出的头类型及下载文件的默认名称
            ExportParams exportParams = new ExportParams(schoolEntity.getName()+canteenEntity.getName()+"营业额 "+
                    dcwmOrderQuery.getQueryDate()+"~"+dcwmOrderQuery.getQueryEndDate(), "营业额统计表", ExcelType.XSSF);
            exportParams.setStyle(ExcelStyleUtil.class);
            String fileName = schoolEntity.getName()+"营业额统计表_"+ dateFormat.format(date) +".xls";

            String fileNames = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileNames);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            List<DcwmOrderRusult> result = shopTurnoverCountService.findTurnoverExportByCanteenId(dcwmOrderQuery);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DcwmOrderRusult.class, result);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("请求 exportDcwmOrder 异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
