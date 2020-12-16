package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 商户主体信息
 */
@Data
public class MerchantSubjectInfo {

    /**
     * 商户签约类型
     *  INDIVIDUAL(个体工商户)：一般为个体户、个体工商户、个体经营。
     *  ENTERPRISE(企业)：一般为有限公司、有限责任公司。
     *  INSTITUTION(事业单位)：包括国内各级、各类政府机构、事业单位等（如：公安、党 团、司法、交通、旅游、工商税务、市政、医疗、教育、学校等机构）。
     */
    private String signType;

    /**
     * 商户证件编号
     * 统一社会信用代码证编号、事业单位法人证书编号、社会团体证书编号等，与商户签约类型匹配。
     */
    private String licenceNo;

    /**
     * 商户证件照片
     * 上传图片前需调用文件上传接口将文件上传至易宝服务器。（请提供证件副本）
     */
    private String licenceUrl;

    /**
     * 商户签约名称
     * 与商户证件主体名称一致。
     */
    private String signName;

    /**
     * 商户简称
     * 将在收银台页面或者支付完成页向买家展示。
     */
    private String shortName;

    /**
     * 开户许可证编号
     * 开户许可证编号和开户许可证照片需要同时上传；如无开户许可证编号，可传基本存款账户编号
     */
    private String openAccountLicenceNo;

    /**
     * 开户许可证照片
     * 为增加商户入网审核通过率，请上传开户许可证；如无开户许可证，请上传基本存款账户信息表照片。上传图片前需调用文件上传接口将文件上传至易宝服务器。
     */
    private String openAccountLicenceUrl;

    /**
     * 手持营业执照在经营场所的照片
     * 上传图片前需调用文件上传接口将文件上传至易宝服务器。
     */
    private String handLicenceUrl;

}
