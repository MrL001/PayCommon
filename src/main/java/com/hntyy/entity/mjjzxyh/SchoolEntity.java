package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 学校
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolEntity extends BaseEntity {

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long schoolId;

    /**
     * 学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private String schoolIdStr;

    /**
     * 名称
     */
    private String name;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 详细地址
     */
    private String address;

}
