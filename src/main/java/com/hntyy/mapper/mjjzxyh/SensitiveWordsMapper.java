package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SensitiveWordsEntity;
import com.hntyy.entity.mjjzxyh.SensitiveWordsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关键词
 */
@Mapper
public interface SensitiveWordsMapper {

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

    void save(SensitiveWordsEntity sensitiveWordsEntity);

    void update(SensitiveWordsEntity sensitiveWordsEntity);

    void deleteById(@Param("id")Integer id);

    void batchDelete(@Param("ids")List<Integer> ids);
}
