package com.wk.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OauthSysUser {
    private String username;
    private String password;
    private List<String> roles;
    private List<String> authorities;
}
