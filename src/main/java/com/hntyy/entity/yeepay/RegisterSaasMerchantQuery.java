package com.hntyy.entity.yeepay;

import lombok.Data;

import java.util.List;

/**
 * 特约商户入网(企业/个体) 查询实体
 */
@Data
public class RegisterSaasMerchantQuery {

    /**
     * 入网请求号
     */
    private String requestNo;

    /**
     * 商户编号
     */
    private String parentMerchantNo;

    /**
     * 入网商户业务角色
     *  ORDINARY_MERCHANT:标准商户
     *  PLATFORM_MERCHANT:平台商
     *  SETTLED_MERCHANT:入驻商户
     */
    private String businessRole;

    /**
     * 商户主体信息
     */
    private MerchantSubjectInfo merchantSubjectInfo;

    /**
     * 商户法人信息
     */
    private MerchantCorporationInfo merchantCorporationInfo;

    /**
     * 商户联系人信息
     */
    private MerchantContactInfo merchantContactInfo;

    /**
     * 行业分类 选填
     */
    private IndustryCategoryInfo industryCategoryInfo;

    /**
     * 经营地址
     */
    private BusinessAddressInfo businessAddressInfo;

    /**
     * 结算账户信息 选填
     */
    private SettlementAccountInfo settlementAccountInfo;

    /**
     * 1、用于接收电子签章地址，完成协议签署；2、用于接收审核已驳回状态下的原因；3、用于接收入网完成的通知。
     */
    private String notifyUrl;

    /**
     * 开通产品信息
     */
    private List<ProductInfo> productInfo;

}
