package com.wk;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author: vince
 * create at: 2019/12/31 18:39
 * @description: 应用启动类
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    // 负载均衡规则，改为随机
    @Bean
    public IRule ribbonRule() {
        return new ZoneAvoidanceRule();
    }
}