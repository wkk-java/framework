package com.wk.spring.user;

import com.wk.entity.user.UserInfo;
import com.wk.spring.feign.UserInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author: vince
 * create at: 2021/3/9 下午6:34
 * @description: 用户工具类
 */
public class UserInfoUtils {
    private static final ThreadLocal<String> USER_TOKEN_HOLDER = new NamedThreadLocal("USER_INFO_TOKEN");
    private static final ThreadLocal<UserInfo> USER_INFO_HOLDER = new NamedThreadLocal("USER_INFO");

    @Autowired
    private static UserInfoFeignService userInfoFeignService;

    @Autowired
    private static RedisTemplate redisTemplate;

    public static String getToken() {
        return USER_TOKEN_HOLDER.get();
    }
    public static void setToken(String token) {
        USER_TOKEN_HOLDER.set(token);
    }

    public static UserInfo getUserInfo() {
        UserInfo userInfo;
        if ((userInfo = USER_INFO_HOLDER.get()) != null) {
            USER_INFO_HOLDER.set(userInfo);
        }
        if (userInfo == null && !StringUtils.isEmpty(getToken())) {
            userInfo = userInfoFeignService.findUserInfo(getToken());
            USER_INFO_HOLDER.set(userInfo);
        }
        return userInfo;
    }

}
