package com.ocean.springboot.util.constant;

public enum DateTimeConstant 
{
	UTC_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
	SIMPLE_DATE_FORMAT("yyyy-MM-dd HH:mm:ss");
	
	private String name;
	
	private DateTimeConstant(String name) 
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
