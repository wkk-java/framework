package com.wk.sys.feign;

import com.wk.sys.feign.impl.FeignServiceTestImpl;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@RibbonClient()
@FeignClient(value = "OAUTH2-SERVER", fallbackFactory = FeignServiceTestImpl.class)
public interface FeignServiceTest {

    @GetMapping(value = "/getAppName")
    String getAppName();
}
