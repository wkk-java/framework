package com.wk.sys.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wk.sys.feign.impl.FeignServiceTestImpl;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@RibbonClient()
@FeignClient(value = "OAUTH2-SERVER", fallbackFactory = FeignServiceTestImpl.class)
public interface FeignServiceTest {

    @HystrixCommand(fallbackMethod = "getAppName")
    @GetMapping(value = "/getAppName")
    String getAppName();

    @GetMapping(value = "/find")
    String find();
}
