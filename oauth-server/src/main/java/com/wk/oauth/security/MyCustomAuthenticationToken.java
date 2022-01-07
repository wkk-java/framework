package com.wk.oauth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class MyCustomAuthenticationToken extends AbstractAuthenticationToken {

    private CustomUserDetails userDetails;

    public MyCustomAuthenticationToken(CustomUserDetails userDetails) {
        super(null);
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    public Object getPrincipal() {
        return this.userDetails;
    }

    public Object getCredentials() {
        return this.userDetails.getPassword();
    }

}