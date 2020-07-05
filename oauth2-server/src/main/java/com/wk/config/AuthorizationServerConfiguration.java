package com.wk.config;

import com.wk.config.model.OAuth2ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Iterator;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @RefreshScope
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalSecret = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        String finalSecret = "{bcrypt}$2a$10$tMSDLeQjFehV8Z1BxoU3quISNyLXXMDI8pdA3RKdwqv1ujuZ6AAQG";
        //配置两个客户端,一个用于password认证一个用于client认证
//        clients.inMemory().withClient("client_1")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("select")
//                .authorities("oauth2")
//                .secret(finalSecret)
//                .and().withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("select")
//                .authorities("oauth2");
        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();
        ClientDetailsServiceBuilder.ClientBuilder builder = null;
        for (Map.Entry<String, OAuth2ClientProperties.Registration> entry : oAuth2ClientProperties.getRegistration().entrySet()) {
            int count = 0;
            String[] scopes = new String[0];
            if (entry.getValue().getScope() != null) {
                scopes = new String[entry.getValue().getScope().size()];
                for (Iterator<String> ite = entry.getValue().getScope().iterator(); ite.hasNext(); count++) {
                    String scope = ite.next();
                    scopes[count] = scope;
                }
            }

            String[] resourceIds = new String[0];
            if (entry.getValue().getResourceId() != null) {
                resourceIds = new String[entry.getValue().getResourceId().size()];
                count = 0;
                for (Iterator<String> ite = entry.getValue().getResourceId().iterator(); ite.hasNext(); count++) {
                    String resourceId = ite.next();
                    resourceIds[count] = resourceId;
                }
            }

            String[] authorities = new String[0];
            if (entry.getValue().getAuthoritie() != null) {
                authorities = new String[entry.getValue().getAuthoritie().size()];
                count = 0;
                for (Iterator<String> ite = entry.getValue().getAuthoritie().iterator(); ite.hasNext(); count++) {
                    String authoritie = ite.next();
                    authorities[count] = authoritie;
                }
            }

            if (builder == null) {
                builder = inMemory.withClient(entry.getValue().getClientId())
                        .resourceIds(resourceIds)
                        .authorizedGrantTypes(entry.getValue().getAuthorizationGrantType().split(","))
                        .scopes(scopes)
                        .authorities(authorities)
                        .secret(entry.getValue().getClientSecret())
                        .refreshTokenValiditySeconds(entry.getValue().getRefreshTokenValiditySeconds())
                        .accessTokenValiditySeconds(entry.getValue().getAccessTokenValiditySeconds());
            } else {
                builder.and().withClient(entry.getValue().getClientId())
                        .resourceIds(resourceIds)
                        .authorizedGrantTypes(entry.getValue().getAuthorizationGrantType().split(","))
                        .scopes(scopes)
                        .authorities(authorities)
                        .secret(entry.getValue().getClientSecret())
                        .refreshTokenValiditySeconds(entry.getValue().getRefreshTokenValiditySeconds())
                        .accessTokenValiditySeconds(entry.getValue().getAccessTokenValiditySeconds());
            }
        }

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //配置解码器
        oauthServer.passwordEncoder(passwordEncoder);
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }

}