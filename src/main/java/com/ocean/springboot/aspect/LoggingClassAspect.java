package com.ocean.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ocean.springboot.annotation.LoggingClass;

@Aspect
@Component	// Use @Component else aspect won't work
public class LoggingClassAspect 
{
	// Prerequisite: Logging.java annotation file is required
	//@Pointcut("@target(com.ocean.springboot.annotation.LoggingClass)")
	//public void anyClassWithLoggingClassAnnotation(LoggingClass loggingClass) {}
			
	@Around("@target(com.ocean.springboot.annotation.LoggingClass)")
	public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		long startTime = System.currentTimeMillis();
		System.out.println("========= Calling method =========");
		Object output = proceedingJoinPoint.proceed();
		System.out.println("========= Execution Complete =========");
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println(String.format("========= Method execution time : %d milliseconds", elapsedTime));
		return output;
	}
}
