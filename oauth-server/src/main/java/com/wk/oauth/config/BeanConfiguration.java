package com.wk.oauth.config;

import com.wk.oauth.exception.CustomWebResponseExceptionTranslator;
import com.wk.oauth.security.CustomAuthorizationTokenServices;
import com.wk.oauth.security.CustomJwtAccessTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;


@Configuration
public class BeanConfiguration {

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

//    @Bean
//    public CustomLogoutHandler customLogoutHandler() {
//        return new CustomLogoutHandler();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new CustomJwtAccessTokenEnhancer();
//        converter.setSigningKey("secret");
//        return converter;
//    }

//    @Bean
//    public AuthorizationServerTokenServices authorizationServerTokenServices() {
//        CustomAuthorizationTokenServices customTokenServices = new CustomAuthorizationTokenServices();
//        customTokenServices.setTokenStore(tokenStore(dataSource()));
//        customTokenServices.setSupportRefreshToken(true);
//        customTokenServices.setReuseRefreshToken(true);
//        customTokenServices.setClientDetailsService(jdbcClientDetailsService);
//        customTokenServices.setTokenEnhancer(accessTokenConverter);
//        return customTokenServices;
//    }

    @Bean
    public JdbcTokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
