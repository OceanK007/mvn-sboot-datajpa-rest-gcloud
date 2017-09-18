package com.ocean.springboot.api.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootApi 
{
	@RequestMapping(value="/")
	public String hello()
	{
		return "Welcome to Spring Boot";
	}
}
