package com.hntyy.entity.mjjzxyh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hntyy.entity.Page;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensitiveWordsQuery extends Page {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private int id;

    /**
     * 名称
     */
    private String word;

}
