package com.example.demo.Controller;

import com.example.demo.Bean.Student;
import com.example.demo.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map test() {
        testService.test();
        Map res = new HashMap();
        return res;
    }
}
