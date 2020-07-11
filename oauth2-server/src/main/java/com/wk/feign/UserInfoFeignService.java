package com.wk.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SYS", fallbackFactory = UserInfoFeignServiceFallbackFactory.class)
public interface UserInfoFeignService {

    @GetMapping(value = "/user/getUserInfo")
    Object getUserInfo(@RequestParam("loginName") String loginName, @RequestParam("pwd") String pwd);

    @GetMapping(value = "/user/loadUserByUsername")
    Object loadUserByUsername(@RequestParam("loginName") String loginName);

}
