package com.ocean.springboot.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInit implements ApplicationRunner	// This will be called on project deployment
{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationInit.class);
	
	@Autowired
	private ApplicationDatabaseSample applicationDatabaseSample;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception 
	{
		logger.info("......................... Application Init .................................");
		
		logger.info("Start: Create sample data for Role");
		applicationDatabaseSample.createRoleData();
		logger.info("END: Create sample data for Role");
	}
}
