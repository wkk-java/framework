package com.wk.filemanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: vince
 * create at: 2021/10/23 下午12:25
 * @description: 启动类
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication//(exclude = {SeataFeignClientAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("启动成功!");
    }
}
