package com.wk.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "sys-server")
public interface UserAccountFeignService {

    @RequestMapping(value = "/user/getUserAccount", method = RequestMethod.GET)
    Map getUserAccount(@RequestParam("userId") String userId);



}
