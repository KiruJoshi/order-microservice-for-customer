package com.example.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

}
