package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.SchoolEntity;
import com.hntyy.mapper.mjjzxyh.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<SchoolEntity> findAll() {
        return schoolMapper.findAll();
    }
}
