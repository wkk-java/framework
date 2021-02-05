package com.wk.oauth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.exception.ExceptionType;
import com.wk.oauth.feign.UserInfoFeignService;
import com.wk.oauth.model.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: vince
 * create at: 2020/7/5 15:25
 * @description: 用户详情
 */
@Log4j2
@Service("userDetailsService")
public class OauthUserDetailsServiveImpl implements UserDetailsService {

    /**
     * .
     */
    @Autowired
    private UserInfoFeignService userInfoFeignService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Object userObject = userInfoFeignService.loadUserByUsername(userName);
        if (userObject instanceof BusinessRuntimeException) {
            String msg = ((BusinessRuntimeException) userObject).getMessage();
            throw new BusinessRuntimeException(ExceptionType.REMARK, msg);
        }
        if (userObject == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo userInfo = JSONObject.parseObject(userObject.toString(), UserInfo.class);
        log.info(JSONObject.toJSONString(userInfo));
//        return new User(userInfo.getUsername(), userInfo.getPassword(), true,
//                false,
//                true,
//                true, userInfo.getAuthorities());
        return userInfo;
    }


}
