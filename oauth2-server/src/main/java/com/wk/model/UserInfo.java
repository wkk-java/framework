package com.wk.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: vince
 * create at: 2019/12/31 18:30
 * @description: 用户信息
 */
@Data
public class UserInfo implements UserDetails, Serializable {
    private String id;
    private String name;
    private String loginName;
    private String password;
    private String clientId;
    private Integer stutus;
    private Integer isLock;
    private Integer isEnable;
    private List<String> sysRoleNames;
    private List<GrantedAuthority> grantedAuthorityList;

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        if (CollectionUtils.isEmpty(grantedAuthorityList)) {
            grantedAuthorityList = new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(sysRoleNames)) {
            return grantedAuthorityList;
        }
        roles.forEach(str -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(str));
        });
        return grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(sysRoleNames);
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLock != 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable == 1;
    }
}
