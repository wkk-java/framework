package com.wk.spring.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: vince
 * create at: 2021/4/1 下午7:50
 * @description:
 */
@Configuration
public class FeignSentinelConfig {

    @Bean
//    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
