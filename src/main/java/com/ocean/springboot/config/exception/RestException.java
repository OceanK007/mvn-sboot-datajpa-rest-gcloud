package com.ocean.springboot.config.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

class RestValidationException
{
	private String className;
	private String fieldName;
	private Object inputValue;
	private String message;
}
public class RestExceptionFormat 
{
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String messageDetail;
	private List<RestValidationException> validationExceptions;
	
	RestExceptionFormat()
	{
		timestamp = LocalDateTime.now();
	}
	
	RestExceptionFormat(HttpStatus httpStatus)
	{
		this();
		this.status = httpStatus;
	}
	
	RestExceptionFormat(HttpStatus httpStatus, Throwable ex)
	{
        this();
        this.status = httpStatus;
        this.message = "Unexpected Error";
        this.messageDetail = ex.getLocalizedMessage();
	}
	
	RestExceptionFormat(HttpStatus httpStatus, String message, Throwable ex)
	{
		this();
		this.status = httpStatus;
		this.message = message;
		this.messageDetail = ex.getLocalizedMessage();
	}
	
	private 
}
