package com.hntyy.mapper.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SensitiveWordsEntity;
import org.apache.ibatis.annotations.Mapper;

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
    List<SensitiveWordsEntity> findAll();

}
