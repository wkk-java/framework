package com.wk.common.model;

import java.io.Serializable;

/**
 * @author: vince
 * create at: 2019/12/31 18:30
 * @description: 用户信息
 */
public class UserInfo implements Serializable {
    private String id;
    private String name;
    private String loginName;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
