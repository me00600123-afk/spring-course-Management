package com.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.global.impl.AuditingImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "configuration")
public class AuditingConfig {
	
	@Bean
	public AuditingImpl configuration() {
		return new AuditingImpl();
	}

}
