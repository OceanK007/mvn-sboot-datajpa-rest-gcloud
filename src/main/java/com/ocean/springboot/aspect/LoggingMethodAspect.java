package com.ocean.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ocean.springboot.annotation.LoggingMethod;

@Aspect
@Component	// Use @Component else aspect won't work
public class LoggingMethodAspect 
{
	// Prerequisite: Logging.java annotation file is required
	@Pointcut("@annotation(com.ocean.springboot.annotation.LoggingMethod)")
	public void anyMethodWithLoggingMethodAnnotation() {}
	
	// Prerequisite: Logging.java annotation file is required
	@Pointcut("execution(@com.ocean.springboot.annotation.LoggingMethod * *(..))")
	public void anyMethodWithLoggingMethodAnnotation2() {}
	
	// Not any prerequisite required
	@Pointcut("execution(public * *(..)) && execution(@com.ocean.springboot.annotation.LoggingMethod * *(..))")
	public void anyPublicMethodAndWithLoggingMethodAnnotation() {}

	/**
	 1st * 	= any access modifier
	 2nd * 	= any class
	 3rd * 	= any method
	 .. 	= any number of parameter and type
	**/
	// Not any prerequisite required	
	@Pointcut("execution(* com.ocean.springboot.api.endpoint.*.*(..)) || execution(* com.ocean.springboot.service.impl.*.*(..)) || execution(* com.ocean.springboot.data.repository.*.*(..))")
	public void onPackage() {}
	
	@Around("anyMethodWithLoggingMethodAnnotation()")
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
