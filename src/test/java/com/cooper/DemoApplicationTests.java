package com.cooper;

import com.cooper.mapper.DataMapper;
import com.cooper.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    DataService dataService;

    @Test
    void contextLoads() {
//       dataService.getCarTraceList("194715","20121107").forEach(System.out::println);
        System.out.println(dataService.getCarFlag().size());
    }

}
