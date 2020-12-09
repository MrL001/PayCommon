package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.Page;
import lombok.Data;

import java.util.List;

/**
 * 骑手订单查询类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiderOrderQuery extends Page {

    /**
     * 姓名
     */
    private String queryRealName;

    /**
     * 手机号
     */
    private String queryPhone;

    /**
     * 查询日期
     */
    private String queryDate;

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long querySchoolId;

}
