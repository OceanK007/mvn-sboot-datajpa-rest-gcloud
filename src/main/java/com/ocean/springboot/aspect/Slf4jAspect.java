package com.ocean.springboot.aspect;

import java.io.InputStream;
import java.net.URL;

import org.apache.tomcat.jni.Pool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Loader;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

@Aspect
@Component
public class Slf4jAspect 
{
	@Before("@annotation(com.ocean.springboot.annotation.Slf4j)")
	public void logBefore(JoinPoint joinPoint) throws NotFoundException
	{
		System.out.println("========================== "+joinPoint.getTarget().getClass().getName());
		//ClassPool pool = ClassPool.getDefault().
		//CtClass point = ClassPool.getDefault().get(joinPoint.getTarget().getClass().getName());
		//System.out.println("---------"+point);
		ClassPool pool = ClassPool.getDefault();
		Loader loader = new Loader(pool);
		CtClass ctClass = pool.get(joinPoint.getTarget().getClass().getName());
		
	}
}
