package com.ocean.springboot.util.constant;

public enum DateTimeConstant 
{
	DATE_FORMAT_JODA("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
	DATE_FORMAT_TIMEZONE("yyyy-MM-dd HH:mm:ss 'GMT'Z"),
	DATE_FORMAT_SIMPLE("yyyy-MM-dd HH:mm:ss");
	
	private String value;
	
	private DateTimeConstant(String value) 
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
}
