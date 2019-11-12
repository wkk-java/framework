package com.wk;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import com.wk.config.SysUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class SpringBoot2Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Oauth2Application.class, args);
        log.info("load userList：" + JSONObject.toJSONString(SysUser.getUserList()));
    }

}