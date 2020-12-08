package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.*;
import com.hntyy.mapper.mjjzxyh.SensitiveWordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;


    @Override
    public List<SensitiveWordsEntity> findAll() {
        return sensitiveWordsMapper.findAll();
    }
}
