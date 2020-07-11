package com.wk.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
 
@Configuration
public class AuthenticationBeanConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
	@Bean
	@ConditionalOnMissingBean(ClientDetailsService.class)
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}
}