package com.hntyy.enums;

public enum  PayTypeEnum {

    ZFB(1,"支付宝"),WX(2,"微信"),BDQB(3,"百度钱包"),PAYPAL(4,"Paypal"),WY(5,"网银"),
    IOS(6,"ios内购"),YE(7,"余额"),GYZF(8,"工银支付"),XXZF(100,"线下支付");

    private final int key;
    private final String value;

    PayTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static  String getNameByKey(Integer key){
        // 未支付
        if (key == null || key == 0){
            return "未支付";
        }
        PayTypeEnum[] payTypeEnums = values();
        for (PayTypeEnum payTypeEnum : payTypeEnums) {
            if (payTypeEnum.getKey()== key) {
                return payTypeEnum.getValue();
            }
        }
        return null;
    }

}
