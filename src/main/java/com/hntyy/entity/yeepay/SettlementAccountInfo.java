package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 结算账户信息
 */
@Data
public class SettlementAccountInfo {

    /**
     * 结算方向  选填
     * ACCOUNT(结算到支付账户)
     * BANKCARD(结算到银行账户)
     * 如果需要开通结算产品，该字段必传；如不需开通结算产品，该结算账户信息都可不传
     */
    private String settlementDirection;

    /**
     * 银行账户类型  选填（结算方向为"结算到银行账户"时必填）
     * 企业：对公账户/单位结算卡
     * 个体户：对公账户/借记卡/存折
     *
     * UNIT_SETTLEMENT_CARD(单位结算卡)
     * ENTERPRISE_ACCOUNT(对公账户)
     * DEBIT_CARD(借记卡)
     * PASSBOOK(存折)
     */
    private String bankAccountType;

    /**
     * 银行账户号码  选填（结算方向为"结算到银行账户"时必填）
     * 对公账户/单位结算卡账户名称默认为商户签约名称
     * 借记卡/存折账户名称默认为商户经营者/法人姓名。
     */
    private String bankCardNo;

    /**
     * 银行账户开户总行编码  选填（结算方向为"结算到银行账户"时必填）
     * 编码请参考易宝支付总行编码。
     */
    private String bankCode;

}
