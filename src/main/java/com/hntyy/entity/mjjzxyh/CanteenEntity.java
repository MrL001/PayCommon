package com.hntyy.entity.mjjzxyh;

import com.hntyy.entity.BaseEntity;
import lombok.Data;

/**
 * 食堂
 */
@Data
public class CanteenEntity extends BaseEntity {

    /**
     * 食堂id
     */
    private Long canteenId;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String icon;

    /**
     * 简介
     */
    private String summary;

    /**
     * 学校id,外键
     */
    private Long schoolId;

}
