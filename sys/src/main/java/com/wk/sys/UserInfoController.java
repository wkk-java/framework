package com.wk.sys;

import com.wk.sys.feign.FeignServiceTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: vince
 * create at: 2019/12/31 18:29
 * @description: 用户控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Qualifier("OAUTH2-SERVER")
    @Autowired
    private FeignServiceTest feignServiceTest;

    @GetMapping(value = "/getInfo")
    public String getAppName() {
        log.info("begin.....");
        return feignServiceTest.getAppName();
    }

}
