package com.wk.gateway.nacos;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = NacosRuleConfig.class)
public class NacosRuleConfig {
 
    @Bean
    public IRule commonRule() {
        return new NacosRule();
    }
}