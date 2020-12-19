package com.cooper.service;

import com.cooper.entity.CarTrace;

import java.util.List;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2020/12/11 7:06 下午
 */
public interface DataService {
    List<CarTrace> getCarTraceList(String car_flag,String time);
    List<String> getCarFlag();
}
