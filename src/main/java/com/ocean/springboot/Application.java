package com.ocean.springboot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*************************************************************************************************************
**************************************************************************************************************
Locate your main application class in a root package above other classes. 

@SpringBootApplication is a convenience annotation that adds all of the following:

# @Configuration tags the class as a source of bean definitions for the application context.
# @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
  Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. 
  This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
# @ComponentScan tells Spring to look for other components, configurations, and services in the defined (com.ocean.springboot) package, allowing it to find the controllers.

*************************************************************************************************************
*************************************************************************************************************/
@SpringBootApplication
public class Application 
{
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) 
	{
		/** The main() method uses Spring Boot’s SpringApplication.run() method to launch an application **/
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * There is also a CommandLineRunner method marked as a @Bean and this runs
	 * on start up. It retrieves all the beans that were created either by your
	 * app or were automatically added thanks to Spring Boot. It sorts them and
	 * prints them out.
	 **/
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext)
	{
		return args -> 
		{
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            logger.info("INFO: Beans provided by spring boot");
            
            String[] beanNames = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) 
            {
                System.out.println(beanName);
            }
        };
	}
}
