package com.wk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wk.feign.UserInfoFeignService;
import com.wk.model.UserInfo;
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
@Service
public class SysUserDetailsServiveImpl implements UserDetailsService {

    @Autowired
    private UserInfoFeignService userInfoFeignService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Object userObject = userInfoFeignService.loadUserByUsername(userName);
        UserInfo userInfo = JSONObject.parseObject(userObject.toString(), UserInfo.class);
        log.info(JSONObject.toJSONString(userInfo));
        return userInfo;
    }


}
