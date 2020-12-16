package com.hntyy.controller.backup;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.hntyy.common.UrlUtil;
import com.hntyy.common.ExcelStyleUtil;
import com.hntyy.entity.PageHelper;
import com.hntyy.entity.mjjzxyh.CanteenEntity;
import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.DcwmOrderRusult;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.service.mjjzxyh.CanteenService;
import com.hntyy.service.mjjzxyh.SchoolService;
import com.hntyy.service.mjjzxyh.ShopTurnoverCountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学校营业额统计（根据学校展示）与TurnoverCountController同步调整  备份
 */
@Slf4j
@RestController
@RequestMapping("/schoolTurnoverCount")
public class SchoolTurnoverCountController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopTurnoverCountService shopTurnoverCountService;

    // 定义全局变量用于导出
    private Long schoolIdd;
//    private Long canteenId;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv,String schoolId) {
        schoolIdd = null;
        // 必须传学校id
        if (StringUtils.isEmpty(schoolId)){
            return null;
        }
        // 查询学校（用于下拉框）解密
        String schoolid = null;
        try {
            schoolid = UrlUtil.deCryptAndDecode(schoolId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SchoolEntity schools = schoolService.findSchoolById(Long.valueOf(schoolid));
        if (schools == null){
            return null;
        }
        schoolIdd = Long.valueOf(schoolid);
        mv.setViewName("/backup/schoolTurnoverCountOrder");
        mv.addObject("schools",schools);
        List<CanteenEntity> canteens = canteenService.findCanteenBySchoolId(schools.getSchoolId());
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

    @RequestMapping("/exportDcwmOrder")
    public void exportDcwmOrder(HttpServletResponse response,DcwmOrderQuery dcwmOrderQuery){
        try {
            // 查询学校&食堂name
            CanteenEntity canteenEntity = canteenService.findCanteenById(dcwmOrderQuery.getCanteenId());
            SchoolEntity schoolEntity = schoolService.findSchoolById(schoolIdd);
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
