package com.ocean.springboot.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.springboot.config.ApplicationProperties;

@RestController
public class SpringBootApi 
{
	@Autowired
	ApplicationProperties applicationProperties;
	
	@RequestMapping(value="/")
	public String hello()
	{
		System.out.println(applicationProperties.getProperty("application.status"));
		return "Welcome to Spring Boot";
	}
}
