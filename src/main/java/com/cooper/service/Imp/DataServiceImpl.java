package com.cooper.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cooper.entity.CarTrace;
import com.cooper.mapper.DataMapper;
import com.cooper.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2020/12/11 7:07 下午
 */
@Service
public class DataServiceImpl implements DataService {
    @Resource
    DataMapper dataMapper;

    @Override
    public List<CarTrace> getCarTraceList(String car_flag,String time) {
        return dataMapper.getCarTraceList(car_flag,time);
    }

    @Override
    public List<String> getCarFlag() {
        return dataMapper.getCarList();
    }
}
