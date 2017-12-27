package com.ocean.springboot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class ApplicationConfiguration 
{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);
	
	// To manually fetch data from application.properties, use this.
	@Autowired
	ApplicationProperties applicationProperties;
	
	/*@Value("${custom.packageToScan}")
	private String PACKAGE_TO_SCAN;
	
	@Value("${custom.hibernate.ddl-auto}")	
	private String DDL;
	
	@Value("${custom.hibernate.dialect}")
	private String DIALECT;
	
	@Value("${custom.hibernate.show_sql}")
	private String SHOW_SQL;
	
	@Value("${custom.hibernate.format_sql}")
	private String FORMAT_SQL;*/
	
	// For explicit configuration, use these.
	/*@Bean
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
	public Properties hibernateProperties()
	{
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", DDL);
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.format_sql", FORMAT_SQL);
		return hibernateProperties;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);		// Replacement of @ComponentScan of @SpringBootApplication annotation
		sessionFactory.setHibernateProperties(hibernateProperties());
		//Properties hibernateProperties = new Properties();
		//hibernateProperties.put("hibernate.hbm2ddl.auto", DDL);
		//sessionFactory.setHibernateProperties(hibernateProperties);
		
		return sessionFactory;
	}*/
	
	
	// Use this when you manually want to configure ehcache manager
	/*@Bean
	public CacheManager cacheManager() 
	{
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() 
	{
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}*/
}
