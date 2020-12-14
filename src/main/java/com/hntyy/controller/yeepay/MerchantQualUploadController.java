package com.hntyy.controller.yeepay;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 子商户入网资质文件上传
 */
@Slf4j
@RestController
@RequestMapping("/merchantQualUpload")
public class MerchantQualUploadController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        return null;
    }



}
