//package com.wk.sys.config;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * .
// */
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Value("${ignoreUrls:/oauth/**}")
//    private String ignoreUrls;
//    @Value("${spring.cloud.config.name}")
//    private static String projectName;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); //关闭csrf
//        if (StringUtils.isNotBlank(ignoreUrls)) {
//            http
//                .requestMatchers().anyRequest()
//                .and().csrf().and()
//                .authorizeRequests()
//                .antMatchers(ignoreUrls).permitAll();
//       }
//    }
//}