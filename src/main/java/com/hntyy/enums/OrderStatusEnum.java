package com.hntyy.enums;

public enum OrderStatusEnum {
    DZF(2,"待支付"),DFH(3,"待发货"),DSH(4,"待收货"),DPJ(5,"待评价"),YQX(6,"已取消"),
    YSC(7,"已删除"),YWC(8,"已完成"),TKDSH(9,"退款待审核"),YTK(10,"已退款"),
    YJJ(11,"已拒绝退款"),DSJJD(31,"待商家接单"),SJYJD(32,"商家已接单"),SJYCC(33,"商家已出餐"),QSZZYS(41,"骑手正在运送"),DDYSD(42,"订单已送达");

    private final int key;
    private final String value;

    OrderStatusEnum(int key, String value) {
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
        OrderStatusEnum[] payTypeEnums = values();
        for (OrderStatusEnum payTypeEnum : payTypeEnums) {
            if (payTypeEnum.getKey()== key) {
                return payTypeEnum.getValue();
            }
        }
        return null;
    }

}
