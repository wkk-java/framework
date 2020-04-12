package com.wk.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OauthSysUser implements Serializable {
    private String username;
    private String password;
    private List<String> roles;
    private List<String> authorities;
}
