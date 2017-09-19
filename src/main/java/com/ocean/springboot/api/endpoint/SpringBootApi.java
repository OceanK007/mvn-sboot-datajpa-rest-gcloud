package com.ocean.springboot.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.springboot.config.ApplicationProperties;

@RestController
public class SpringBootApi 
{
	private static final Logger logger = LoggerFactory.getLogger(SpringBootApi.class);
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@RequestMapping(value="/")
	public String hello()
	{
		logger.info("INFO: "+applicationProperties.getProperty("application.status"));
		logger.debug("DEBUG: "+applicationProperties.getProperty("application.status"));
		return "Welcome to Spring Boot";
	}
}
