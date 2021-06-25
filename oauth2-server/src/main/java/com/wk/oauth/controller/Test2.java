package com.wk.oauth.controller;

import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.exception.ExceptionType;
import com.wk.oauth.feign.UserInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class Test2 {

    @Value("${application.author.names:hhhhh}")
    private String author;

    @Autowired
    private UserInfoFeignService userInfoFeignService;

    @GetMapping("/app/getConfig")
    public String getConfig() {
        return "author is " + author;
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @GetMapping("/order/find")
    public String getOrder() {
        throw new BusinessRuntimeException(ExceptionType.REMARK, "查找失败...");
    }

    @GetMapping(value = "/user/getUserInfo")
    public Object getUserInfo(@RequestParam("loginName") String loginName, @RequestParam("pwd") String pwd) {
        Object obj = userInfoFeignService.getUserInfo(loginName, pwd);
        return obj;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode(""));
    }

}