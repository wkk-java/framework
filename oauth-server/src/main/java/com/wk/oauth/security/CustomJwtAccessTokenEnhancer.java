package com.wk.oauth.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomJwtAccessTokenEnhancer extends JwtAccessTokenConverter implements Serializable {
    private static int authenticateCodeExpiresTime = 10 * 60;

    private static final String TOKEN_SEG_CLIENT = "clientId";
    private static final String TOKEN_SEG_USER_ID = "userId";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
//        String userId = authentication.getUserAuthentication().getName();
        Map<String, Object> info = new HashMap<>();
//        info.put(TOKEN_SEG_USER_ID, userId);

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        String clientId = authentication.getOAuth2Request().getClientId();

        OAuth2AccessToken enhancedToken = super.enhance(customAccessToken, authentication);
        enhancedToken.getAdditionalInformation().put(TOKEN_SEG_CLIENT, clientId);
        return enhancedToken;
    }

    @Override
    public void setSigningKey(String key) {
        super.setSigningKey("123456");
    }
}
