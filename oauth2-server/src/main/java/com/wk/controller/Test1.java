package com.wk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: vince
 * create at: 2020/6/21 22:26
 * @description: test
 */
@RefreshScope
@RestController
public class Test1 {
    @Value("${spring.application.name:defaultName}")
    private String appName;
    @Value("${application.author.address:defaultAddr}")
    private String address;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping(value = "/getAppName")
    public String getAppName() {
        return "name:" + appName + ",addr:" + address + ",port:" + serverPort;
    }

}
