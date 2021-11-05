package com.wk.spring.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootAdminConfig {

    @Bean
	public InMemoryHttpTraceRepository getInMemoryHttpTrace(){
		return new InMemoryHttpTraceRepository();
	}

	@Bean
	public InMemoryAuditEventRepository getInMemoryAuditEventRepository() {
    	return new InMemoryAuditEventRepository();
	}

}
