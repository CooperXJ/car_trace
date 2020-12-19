package com.cooper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cooper.entity.CarTrace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2020/12/11 5:21 下午
 */
@Mapper
public interface DataMapper extends BaseMapper<CarTrace> {
    public List<CarTrace> getCarTraceList(@Param("car_flag") String car_flag,@Param("time") String time);
    public List<String> getCarList();
}
