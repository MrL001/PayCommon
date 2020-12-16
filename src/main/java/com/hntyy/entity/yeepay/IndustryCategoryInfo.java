package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 行业分类
 */
@Data
public class IndustryCategoryInfo {

    /**
     * 商户一级行业分类
     * 要求按照商户实际经营场景选择对应的行业分类编码，编码请参考易宝支付行业经营类目
     */
    private String primaryIndustryCategory;

    /**
     * 商户二级行业分类
     * 要求按照商户实际经营场景选择对应的行业分类编码，编码请参考易宝支付行业经营类目
     */
    private String secondaryIndustryCategory;

    /**
     * 行业相关资质  选填
     * 上传图片前需调用文件上传接口将文件上传至易宝服务器。
     */
    private String industryQualificationUrl;

}
