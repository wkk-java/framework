//package com.wk.zuul.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableOAuth2Sso
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable(); //关闭csrf
////        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证
//
//        http
////        //需要授权的url
////                .authorizeRequests()
////                .antMatchers("/token").permitAll()
////                .anyRequest().authenticated()
////                .and()
//                .csrf().disable();
//        super.configure(http);
//    }
//}