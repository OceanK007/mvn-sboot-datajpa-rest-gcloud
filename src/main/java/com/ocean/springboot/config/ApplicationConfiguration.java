package com.ocean.springboot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class ApplicationConfiguration 
{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);
	
	// To manually fetch data from application.properties, use this.
	@Autowired
	ApplicationProperties applicationProperties;
	
	// To fetch data using annotation from application.properties, use this.
	@Value("${custom.hibernate.ddl-auto}")	
	private String DDL;
	
	@Value("${custom.packageToScan}")
	private String PACKAGE_TO_SCAN;
	
	@Bean
	public DataSource dataSource() 
	{
		DataSourceBuilder dataSource = DataSourceBuilder.create();
		dataSource.driverClassName(applicationProperties.getProperty("custom.driverClassName"));
		dataSource.url(applicationProperties.getProperty("custom.url"));
		dataSource.username(applicationProperties.getProperty("custom.username"));
		dataSource.password(applicationProperties.getProperty("custom.password"));
		
		logger.info("INFO: DataSource created");
		return dataSource.build();   
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", DDL);
		sessionFactory.setHibernateProperties(hibernateProperties);
		
		return sessionFactory;
	}
}
