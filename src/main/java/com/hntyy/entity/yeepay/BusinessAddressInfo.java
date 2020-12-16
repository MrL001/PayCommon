package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 经营地址信息
 */
@Data
public class BusinessAddressInfo {

    /**
     * 商户实际经营地所在省
     * 要求按照商户实际经营地址选择对应的省编号，编码请参考易宝支付经营省市区编码。
     */
    private String province;

    /**
     * 商户实际经营地所在市
     * 要求按照商户实际经营地址选择对应的市编号，编码请参考易宝支付经营省市区编码。
     */
    private String city;

    /**
     * 商户实际经营地所在区
     * 要求按照商户实际经营地址选择对应的市编号，编码请参考易宝支付经营省市区编码。
     */
    private String district;

    /**
     * 商户实际经营详细地址
     * 不需要再次上送省市区。
     */
    private String address;

}
