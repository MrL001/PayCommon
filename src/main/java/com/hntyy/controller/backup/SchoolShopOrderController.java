package com.hntyy.controller.backup;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hntyy.common.ExcelStyleUtil;
import com.hntyy.common.UrlUtil;
import com.hntyy.entity.mjjzxyh.DcwmOrderQuery;
import com.hntyy.entity.mjjzxyh.OrderDetailEntity;
import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.entity.mjjzxyh.ShopOrderEntity;
import com.hntyy.enums.OrderStatusEnum;
import com.hntyy.enums.PayTypeEnum;
import com.hntyy.service.mjjzxyh.OrderDetailService;
import com.hntyy.service.mjjzxyh.OrderService;
import com.hntyy.service.mjjzxyh.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学校商品订单导出 与ShopOrderController同步调整  备份
 */
@Slf4j
@RestController
@RequestMapping("/schoolShopOrder")
public class SchoolShopOrderController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    // 定义全局变量用于导出
    private Long schoolIdd;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv,String schoolId) {
        schoolIdd = null;
        mv.setViewName("/backup/schoolShopOrder");
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
        mv.addObject("schools",schools);
        // 查询所有配送方式 （用于下拉框） 1配送，2自取，3堂食
        Map<Integer,String> deliveryModeS = new HashMap<>();
        deliveryModeS.put(1,"配送");
        deliveryModeS.put(2,"自取");
        deliveryModeS.put(3,"堂食");
        mv.addObject("deliveryModeS",deliveryModeS);
        return mv;
    }

    @RequestMapping("/exportDcwmOrder")
    public void exportDcwmOrder(HttpServletResponse response, DcwmOrderQuery dcwmOrderQuery){
        try {
            dcwmOrderQuery.setSchoolId(schoolIdd);
            if (dcwmOrderQuery.getDeliveryMode().intValue() == 0){
                dcwmOrderQuery.setDeliveryMode(null);
            }

            // 设置响应输出的头类型及下载文件的默认名称
            ExportParams exportParams = new ExportParams("校源汇点餐外卖订单数据", "校源汇点餐外卖订单数据"+dcwmOrderQuery.getQueryDate()+"~"+dcwmOrderQuery.getQueryEndDate(), ExcelType.XSSF);
            exportParams.setStyle(ExcelStyleUtil.class);

            String fileName = "校源汇点餐外卖订单数据.xls";
            String fileNames = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileNames);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            List<ShopOrderEntity> result = orderService.exportShopOrderList(dcwmOrderQuery);

            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);

            for(ShopOrderEntity shopOrderEntity:result){
                // 店铺信息
                StringBuffer shopInfo = new StringBuffer("学校：").append(shopOrderEntity.getSchoolName()).append("\n");
                shopInfo.append("类型：").append(shopOrderEntity.getShopType() == 1 ? "外卖点餐":"购物商城").append("\n");
                shopInfo.append("名称：").append(shopOrderEntity.getShopName());
                shopOrderEntity.setShopInfo(shopInfo.toString());
                // 支付信息
                StringBuffer payInfo = new StringBuffer("支付类型：").append(PayTypeEnum.getNameByKey(shopOrderEntity.getPayType())).append("\n");
                payInfo.append("包装费：").append(shopOrderEntity.getBoxFee()).append("\n");
                payInfo.append("配送费：").append(shopOrderEntity.getDeliveryFee()).append("\n");
                payInfo.append("新客立减：").append(shopOrderEntity.getNewCustomerReduction()).append("\n");
                payInfo.append("满减：").append(shopOrderEntity.getFullReductionMoney()).append("\n");
                payInfo.append("优惠劵：").append(shopOrderEntity.getCouponMoney()).append("\n");
                payInfo.append("总优惠金额：").append(shopOrderEntity.getTotalReferential()).append("\n");
                payInfo.append("总费用：").append(shopOrderEntity.getTotalPrice());
                shopOrderEntity.setPayInfo(payInfo.toString());
                List<OrderDetailEntity> orderDetailByOrderId = orderDetailService.findOrderDetailByOrderId(shopOrderEntity.getOrderId());
                shopOrderEntity.setOrderDetailEntityList(orderDetailByOrderId);
                // 商品信息
                StringBuffer orderDetail = new StringBuffer();
                for (OrderDetailEntity orderDetailEntity:orderDetailByOrderId){
                    orderDetail.append("名称：").append(orderDetailEntity.getName()).append("\n");
                    orderDetail.append("类型：").append(orderDetailEntity.getMerCateName()).append("\n");
                    orderDetail.append("规格：").append(orderDetailEntity.getSkuvalue() == null ? "":orderDetailEntity.getSkuvalue()).append("\n");
                    orderDetail.append("单价：").append(orderDetailEntity.getUnitPrice()).append("\n");
                    orderDetail.append("数量：").append(orderDetailEntity.getNumber()).append("\n");
                    orderDetail.append("总价：").append(orderDetailEntity.getTotalPrice()).append("\n");
                }
                shopOrderEntity.setOrderDetail(orderDetail.toString());
                // 物流
                if (shopOrderEntity.getDeliveryMode().intValue() ==1){
                    shopOrderEntity.setDeliveryModeStr("配送");
                } else if (shopOrderEntity.getDeliveryMode().intValue() ==2){
                    shopOrderEntity.setDeliveryModeStr("自取");
                } else {
                    shopOrderEntity.setDeliveryModeStr("堂食");
                }
                // 收货信息
                StringBuffer receiptInfo = new StringBuffer();
                if (shopOrderEntity.getDeliveryMode().intValue() ==1){
                    receiptInfo.append("姓名：").append(shopOrderEntity.getReceiptName()).append("\n");
                    receiptInfo.append("性别：").append(shopOrderEntity.getReceiptSex() == 1 ? "男性":"女性").append("\n");
                    receiptInfo.append("手机号：").append(shopOrderEntity.getReceiptPhone()).append("\n");
                    receiptInfo.append("收货地址：").append(shopOrderEntity.getReceiptProvince()+shopOrderEntity.getReceiptCity()+
                            shopOrderEntity.getReceiptArea()+shopOrderEntity.getReceiptAddress()).append("\n");
                } else {
                    receiptInfo.append("自取时间：").append(shopOrderEntity.getSelfTakeDate() == null ? "":simpleDateFormat.format(shopOrderEntity.getSelfTakeDate())).append("\n");
                    receiptInfo.append("预留手机号：").append(shopOrderEntity.getPhone()).append("\n");
                }
                shopOrderEntity.setReceiptInfo(receiptInfo.toString());
                // 时间
                StringBuffer time = new StringBuffer();
                time.append("下单：").append(shopOrderEntity.getCreateDate() == null ? "":simpleDateFormat.format(shopOrderEntity.getCreateDate())).append("\n");
                time.append("支付：").append(shopOrderEntity.getPaymentDate() == null ? "":simpleDateFormat.format(shopOrderEntity.getPaymentDate())).append("\n");
                time.append("发货：").append(shopOrderEntity.getUpdateDate() == null ? "":simpleDateFormat.format(shopOrderEntity.getUpdateDate()));
                shopOrderEntity.setTime(time.toString());
                // 状态
                shopOrderEntity.setStatusStr(OrderStatusEnum.getNameByKey(shopOrderEntity.getStatus()));
            }
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), ShopOrderEntity.class, result);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("请求 exportDcwmOrder 异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
