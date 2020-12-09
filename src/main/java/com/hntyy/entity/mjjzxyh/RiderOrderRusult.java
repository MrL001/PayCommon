package com.hntyy.entity.mjjzxyh;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 骑手订单列表 展示Rusult
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiderOrderRusult implements Serializable {

    /**
     * 骑手id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long riderAccountId;

    /**
     * 骑手name
     */
    @Excel(name = "姓名", width = 25,orderNum = "1")
    private String riderName;

    /**
     * 手机
     */
    @Excel(name = "手机", width = 25,orderNum = "1")
    private String phone;

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long schoolId;

    /**
     * 学校name
     */
    private String schoolName;

    /**
     * 总订单量
     */
    @Excel(name = "总订单量", width = 25,orderNum = "2")
    private int orderNums;

}
