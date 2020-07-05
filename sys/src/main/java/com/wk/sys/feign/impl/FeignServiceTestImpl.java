package com.wk.sys.feign.impl;

import com.wk.sys.feign.FeignServiceTest;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author: vince
 * create at: 2020/6/24 01:08
 * @description:
 */
@Log4j2
@Component
public class FeignServiceTestImpl implements FallbackFactory<FeignServiceTest>, FeignServiceTest{

    @Override
    public String getAppName() {
        log.info("报错啦....,{0}", cause);
        return "报错啦....";
    }

    @Override
    public String find() {
        log.error(cause);
        return null;
    }

    @Override
    public FeignServiceTest create(Throwable throwable) {
        this.cause = throwable;
        return this;
    }

    private Throwable cause;


}
