package com.hntyy.service.mjjzxyh;

import com.hntyy.entity.mjjzxyh.CanteenEntity;
import com.hntyy.mapper.mjjzxyh.CanteenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private CanteenMapper canteenMapper;

    @Override
    public List<CanteenEntity> findCanteenBySchoolId(Long schoolId) {
        return canteenMapper.findCanteenBySchoolId(schoolId);
    }
}
