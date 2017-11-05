package com.ocean.springboot.config.exception;

import java.util.HashMap;
import java.util.Map;

public class ApplicationGenericException extends Exception 
{
	private int statusCode;				// For error code like 404, 500, etc
	private String statusType;			// For exception type. Like "Bad Request"
	private String message;
	private Map<String, String> exceptionMap;
	
	public ApplicationGenericException(String message) 
	{
		super(message);
		this.message = message;
	}
	
	public ApplicationGenericException(String message, Map<String, String> exceptionMap)
	{
		this(message);
		
		if(this.exceptionMap == null)
			this.exceptionMap = new HashMap<String, String>();
		
		this.exceptionMap.putAll(exceptionMap);
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

	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}

	public void setExceptionMap(Map<String, String> exceptionMap) {
		this.exceptionMap = exceptionMap;
	}
}
