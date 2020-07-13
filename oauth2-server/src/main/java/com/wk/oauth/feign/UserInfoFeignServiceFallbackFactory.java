package com.wk.oauth.feign;

import com.alibaba.fastjson.JSONObject;
import com.wk.common.exception.BusinessRuntimeException;
import com.wk.common.exception.ExceptionType;
import com.wk.oauth.model.UserInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author: vince
 * create at: 2020/7/4 21:52
 * @description:
 */
@Log4j2
@Component
public class UserInfoFeignServiceFallbackFactory implements UserInfoFeignService, FallbackFactory<UserInfoFeignService> {

    @Override
    public UserInfo getUserInfo(String loginName, String pwd) {
        if (throwable instanceof BusinessRuntimeException) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, JSONObject.toJSONString(throwable));
        }
        log.error("调用发生错误了，{0}", throwable);
        return null;
    }

    @Override
    public Object loadUserByUsername(String loginName) {
        if (throwable instanceof BusinessRuntimeException) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, JSONObject.toJSONString(throwable));
        }
        log.error("调用发生错误了，{0}", throwable);
        return null;
    }

    private Throwable throwable;

    @Override
    public UserInfoFeignService create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }
}
