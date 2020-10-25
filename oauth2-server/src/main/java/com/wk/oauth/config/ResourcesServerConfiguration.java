package com.wk.oauth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableResourceServer
@Configuration
@RefreshScope
public class ResourcesServerConfiguration  extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Value("${spring.scurity.oauth.registResourceIds: oauth}")
    private String registResourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        //tokenService.setCheckTokenEndpointUrl(URL);
//        tokenService.setClientId("client_id");
//        tokenService.setClientSecret("secret");
        resources.tokenServices(tokenService);
        if (StringUtils.isNotBlank(registResourceIds)) {
            resources.resourceId(registResourceIds);
        }
        resources.tokenStore(tokenStore);
    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
//                .and()
//                .headers().addHeaderWriter((request, response) -> {
//            response.addHeader("Access-Control-Allow-Origin", "*");
//            if (request.getMethod().equals("OPTIONS")) {
//                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
//                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
//            }
//        });
//    }
}
