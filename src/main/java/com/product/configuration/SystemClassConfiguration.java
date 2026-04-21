package com.product.configuration;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemClassConfiguration {
	
	@Bean("random")
	public Random createRandomBean() {
		return new Random();
	}
	
	@Bean("otpHolder")
	public Map<String,Object[]> getUserVerificationMap(){
		ConcurrentHashMap<String, Object[]> map=new ConcurrentHashMap<String, Object[]>();
		return map;
	}


}
