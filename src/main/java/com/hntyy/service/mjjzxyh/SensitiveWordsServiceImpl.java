package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.mapper.mjjzxyh.SensitiveWordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;


    @Override
    public List<SensitiveWordsEntity> findAllSensitiveWords() {
        return sensitiveWordsMapper.findAllSensitiveWords();
    }

    @Override
    public List<SensitiveWordsEntity> findAll(SensitiveWordsQuery sensitiveWordsQuery) {
        return sensitiveWordsMapper.findAll(sensitiveWordsQuery);
    }

    @Override
    public int findAllCount(SensitiveWordsQuery sensitiveWordsQuery) {
        return sensitiveWordsMapper.findAllCount(sensitiveWordsQuery);
    }

    @Override
    public void save(SensitiveWordsEntity sensitiveWordsEntity) {
        sensitiveWordsMapper.save(sensitiveWordsEntity);
    }

    @Override
    public void update(SensitiveWordsEntity sensitiveWordsEntity) {
        sensitiveWordsMapper.update(sensitiveWordsEntity);
    }

    @Override
    public void deleteById(Integer id) {
        sensitiveWordsMapper.deleteById(id);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        sensitiveWordsMapper.batchDelete(ids);
    }

}
