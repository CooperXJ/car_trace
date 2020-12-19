package com.cooper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2020/12/11 5:22 下午
 */
@Data
@ToString
public class CarTrace {
    @TableField(value = "car_flag")
    private int car_flag;
    @TableField(value = "trigger_event")
    private int trigger_event;
    private int status;
    private String time;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private int speed;
    private int position;
    @TableField(value = "GPS_flag")
    private int GPS_flag;
}
