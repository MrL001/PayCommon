package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;

import java.util.List;

public interface SensitiveWordsService {

    /**
     * 查询所有关键词
     * @return
     */
    List<SensitiveWordsEntity> findAll(SensitiveWordsQuery sensitiveWordsQuery);

    /**
     * 查询所有关键词总数
     * @return
     */
    int findAllCount(SensitiveWordsQuery sensitiveWordsQuery);

}
