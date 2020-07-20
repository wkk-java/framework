package com.wk.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@SpringBootApplication
@EnableZuulProxy
public class Application {

    //配置动态刷新
    @RefreshScope
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
