package com.ocean.springboot.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.springboot.annotation.LoggingClass;
import com.ocean.springboot.annotation.LoggingMethod;
import com.ocean.springboot.config.ApplicationProperties;

@RestController
//@LoggingClass
public class SpringBootApi 
{
	private static final Logger logger = LoggerFactory.getLogger(SpringBootApi.class);
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	// To fetch data using annotation & expression from application.properties, use this.
	@Value("${application.status}")
	private String APPLICATION_STATUS;
	
	@LoggingMethod
	@RequestMapping(value="/")
	public String hello()
	{
		logger.info("INFO: "+applicationProperties.getProperty("application.status"));
		logger.info("INFO: "+APPLICATION_STATUS);
		logger.debug("DEBUG: "+applicationProperties.getProperty("application.status"));
		return "Welcome to Spring Boot";
	}
}
