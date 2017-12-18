package com.ocean.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)	// On class level
//@Target({ElementType.TYPE, ElementType.METHOD})	// On class & method level
public @interface LoggingClass
{

}
