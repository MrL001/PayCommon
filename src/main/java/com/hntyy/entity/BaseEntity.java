package com.hntyy.entity;

import lombok.Data;
import java.util.Date;

@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时
     */
    private Date updateDate;

}
