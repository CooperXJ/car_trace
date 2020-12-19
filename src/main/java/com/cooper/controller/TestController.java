package com.cooper.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cooper.entity.CarTrace;
import com.cooper.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2020/12/11 5:04 下午
 */
@Controller
@CrossOrigin
public class TestController {
    @Resource
    DataService dataService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray test(){
        JSONArray jsonRes = new JSONArray();
        int i = 0;
        for (String s : dataService.getCarFlag()) {
            System.out.println("--------0-----这是第"+i);
            i++;
            JSONArray jsonArray = new JSONArray();
            String data = "20121107";
            for (CarTrace carTrace : dataService.getCarTraceList(s,data)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("x",carTrace.getLongitude());
                jsonObject.put("y",carTrace.getLatitude());
                jsonArray.add(jsonObject);
            }
            jsonRes.add(jsonArray);
        }
            return jsonRes;
        }
}
