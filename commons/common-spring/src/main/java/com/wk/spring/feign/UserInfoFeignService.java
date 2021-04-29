package com.wk.spring.feign;

import com.wk.entity.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sys-server/user")
public interface UserInfoFeignService {

    @GetMapping(value = "/findUserInfo")
    UserInfo findUserInfo(@RequestParam("token") String token);
}