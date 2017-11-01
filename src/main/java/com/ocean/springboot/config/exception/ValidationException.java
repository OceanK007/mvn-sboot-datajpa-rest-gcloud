package com.ocean.springboot.config.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Throwable 
{
	private int statusCode;				// For error code like 404, 500, etc
	private String statusType;			// For exception type. Like "Bad Request"
	private String message;
	private Map<String, String> validationExceptionMap;
	
	public ValidationException(String message) 
	{
		super(message);
	}
	
	public ValidationException(Map<String, String> exceptionMap)
	{
		if(validationExceptionMap == null)
			validationExceptionMap = new HashMap<String, String>();
		
		validationExceptionMap.putAll(exceptionMap);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getValidationExceptionMap() {
		return validationExceptionMap;
	}

	public void setValidationExceptionMap(Map<String, String> validationExceptionMap) {
		this.validationExceptionMap = validationExceptionMap;
	}
}
