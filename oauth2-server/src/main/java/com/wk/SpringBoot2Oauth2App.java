package com.wk;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import com.wk.config.SysUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBoot2Oauth2App {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Oauth2App.class, args);
        log.info("load userList：" + JSONObject.toJSONString(SysUser.getUserList()));
    }

}