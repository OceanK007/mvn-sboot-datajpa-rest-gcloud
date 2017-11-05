package com.ocean.springboot.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)	// To remove null fields from json
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

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
