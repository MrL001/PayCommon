package com.hntyy.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageHelper<T> {
    // 注意：这两个属性名称不能改变，是定死的
    // 实体类集合
    private List<T> rows = new ArrayList<T>();

    // 数据总条数
    private int total;

    /**
     * 根据需求自定义参数
     */
    // 订单总量
    private int orderSums;

}
