package com.hntyy.entity.mjjzxyh;

import com.hntyy.entity.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SchoolEntity extends BaseEntity {

    /**
     * 学校id
     */
    private Long schoolId;

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
