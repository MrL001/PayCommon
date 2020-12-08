package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.BaseEntity;
import lombok.Data;

/**
 * 关键词
 */
@Data
public class SensitiveWordsEntity {

    /**
     * id
     */
    private int id;

    /**
     * 名称
     */
    private String word;

}
