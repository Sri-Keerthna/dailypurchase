package com.sprintstrickers.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Sri Keerthna
 * 
 * RestConfig
 * 
 * This method has been created to create
 * bean of RestTemplate
 *
 */

@Configuration
public class RestConfig {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
