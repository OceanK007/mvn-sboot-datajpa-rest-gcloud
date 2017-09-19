package com.ocean.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/*************************************************************************
************************************************************************** 
	// Note the order is from least to most specific (last one wins)
	private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
	
	file:./ resolve to the working directory where you start the java process. So can be useful when you want app.properties outside .jar/.war file
	classpath:/ resolve to project directory
	
	classpath:/ by default it looks in src/main/resources folder. To scan in particular folder structure use classpath:/com/folder/app.properties
*************************************************************************
************************************************************************/

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties 
{
	@Autowired
	private Environment environment;
	
	public String getProperty(String property)
	{
		return environment.getProperty(property);
	}
}
