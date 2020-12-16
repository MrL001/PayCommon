package com.hntyy.entity.yeepay;

import lombok.Data;

/**
 * 开通产品信息
 */
@Data
public class ProductInfo {

    /**
     * 产品码
     * 产品码请参考收付款解决方案产品列表-服务商版-1117更新
     */
    private String productCode;

    /**
     * 计费策略
     * SINGLE_PERCENT:单笔百分比
     * SINGLE_FIXED:单笔固定值
     * FIXED_MIX_PERCENT:单笔固定值 + 单笔百分比
     * PERCENT_MIX_CAP:单笔百分比 + 封顶值。
     */
    private String rateType;

    /**
     * 单笔百分比  选填
     * 计费策略为 SINGLE_PERCENT、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。
     * 该字段单位：%；如 0.5%，上传 0.5 即可。
     */
    private String percentRate;

    /**
     * 单笔固定值  选填
     * 计费策略为 SINGLE_FIXED、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。
     * 该字段单位：元/笔；如 5 元/笔，上传 5 即可。
     */
    private String fixedRate;

}
