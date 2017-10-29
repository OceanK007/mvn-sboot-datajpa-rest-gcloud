package com.ocean.springboot.config.exception;

public class RestValidationException 
{
	private String objectName;
	private String fieldName;
	private Object rejectedValue;
	private String message;
	
	public RestValidationException(String objectName, String fieldName, Object rejectedValue, String message) 
	{
		this.objectName = objectName;
		this.fieldName = fieldName;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
}
