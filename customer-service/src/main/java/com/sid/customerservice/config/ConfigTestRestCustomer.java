package com.sid.customerservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RefreshScope
@RestController
public class ConfigTestRestCustomer {

    @Value("${global.param.p1}")
private String a;
    @Value("${global.param.p2}")
    private String b;

    @Autowired
    private CustomerConfigParams params;

    @GetMapping("/testConfig1")
    public Map<String,String> configTest(){
        return Map.of("a",a,"b",b);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2(){
        return params;
    }



}
