package com.wk.oauth.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: vince
 * create at: 2020/6/21 22:26
 * @description: test
 */
@Slf4j
@RefreshScope
@RestController
public class Test1 {
    @Value("${spring.application.name:defaultName}")
    private String appName;
    @Value("${spring.application.author.address:defaultAddr}")
    private String address;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping(value = "/getAppName")
    public String getAppName() {
        return "name:" + appName + ",addr:" + address + ",port:" + serverPort;
    }

    @GetMapping(value = "/getCurrentUserInfo")
    public void getCurrentUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal:{}", JSONObject.toJSONString(principal));
    }
}
