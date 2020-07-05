package com.wk.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("application.init")
public class SysUser {

    /**
     * 测试用户.
     */
    static List<OauthSysUser> users;

    public void setUsers(List<OauthSysUser> users) {
        this.users = users;
    }
    public static List<OauthSysUser> getUserList() {
        return users;
    }
}