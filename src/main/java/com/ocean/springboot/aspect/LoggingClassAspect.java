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
	// Prerequisite: LoggingClass.java annotation file is required
	// You have to explicitly define @LoggingClass on class 
	// This pointcut would advise all public methods of a class annotated with @LoggingClass
	@Pointcut("execution(public * *(..)) && within(@com.ocean.springboot.annotation.LoggingClass *)")
	public void anyPublicMethodOfClassWithLoggingClassAnnotation() {}
			
	@Around("anyPublicMethodOfClassWithLoggingClassAnnotation()")
	public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		System.out.println("========================================================================================================");
		System.out.println("======================================== START: Method Execution: "+proceedingJoinPoint.getSignature().toString());
		//System.out.println("Method Name: "+proceedingJoinPoint.getSignature().getName());
		//System.out.println("Method Name: "+proceedingJoinPoint.getSignature().toShortString());
		//System.out.println("Method Return Type & Signature: "+proceedingJoinPoint.getSignature().toString());
		//System.out.println("Method Args: "+Arrays.asList(proceedingJoinPoint.getArgs()));
		long startTime = System.currentTimeMillis();
		Object output = proceedingJoinPoint.proceed();
		System.out.println(String.format("=> Method execution time : %d milliseconds", System.currentTimeMillis() - startTime));
		System.out.println("======================================== END: Method Execution: "+proceedingJoinPoint.getSignature().toString());
		System.out.println("========================================================================================================");
		return output;
	}
}
