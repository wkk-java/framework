package com.wk.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author: vince
 * create at: 2020/7/26 18:06
 * @description: zuulConfig
 */
@Configuration
public class ZullConfig {

    @Bean(name = "myZullDynamicConfig")
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

}
