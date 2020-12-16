package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 商户法人信息
 */
@Data
public class MerchantCorporationInfo {

    /**
     * 法人姓名
     * 请填写经营者/法人对应身份证件的姓名。
     */
    private String legalName;

    /**
     * 法人证件类型
     * ID_CARD(法人身份证)
     * PASSPORT(护照)
     * HMT_VISITORPASS(港澳台居民往来内地通行证)
     * SOLDIER(士兵证)
     * OFFICERS(军官证)
     * OVERSEAS_CARD(境外证件)
     * 不传默认身份证。
     */
    private String legalLicenceType;

    /**
     * 法人证件号码
     * 请填写经营者/法人对应身份证件号码。
     */
    private String legalLicenceNo;

    /**
     * 法人证件正面照片
     * 请上传带有人像面的法人证件照片；
     *上传图片前需调用文件上传接口将文件上传至易宝服务器。
     */
    private String legalLicenceFrontUrl;

    /**
     * 法人证件反面照片
     * 如为身份证，请上传国徽面照片；
     * 其余法人证件（如港澳台通行证）请上传人像面反面照片；
     * 如该类型法人证件无反面（护照），再次上传正面照片即可。
     * 上传图片前需调用文件上传接口将文件上传至易宝服务器。
     */
    private String legalLicenceBackUrl;

}
