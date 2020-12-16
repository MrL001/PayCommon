package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 商户联系人信息
 */
@Data
public class MerchantContactInfo {

    /**
     * 商户联系人姓名
     * 用于商户与易宝之间的业务联系，请按照真实联系人信息填写。
     */
    private String contactName;

    /**
     * 商户联系人证件号码 选填
     * 用于商户与易宝之间的业务联系，请按照真实联系人信息填写。不传默认法人证件号。
     */
    private String contactLicenceNo;

    /**
     * 商户联系人手机号
     * 用于商户与易宝之间的业务联系，请按照真实联系人信息填写。
     */
    private String contactMobile;

    /**
     * 商户联系人邮箱
     * 1.可能用于商户使用的产品/服务升级维护通知发送。
     * 2.可能用于商户通道报备。
     */
    private String contactEmail;

}
