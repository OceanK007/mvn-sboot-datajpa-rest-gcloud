package com.ocean.springboot.config.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestException 
{
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String messageDetail;
	private List<RestValidationException> validationExceptions;
	
	RestException()
	{
		timestamp = LocalDateTime.now();
	}
	
	RestException(HttpStatus httpStatus)
	{
		this();
		this.status = httpStatus;
	}
	
	RestException(HttpStatus httpStatus, Throwable ex)
	{
        this();
        this.status = httpStatus;
        this.message = "Unexpected Error";
        this.messageDetail = ex.getLocalizedMessage();
	}
	
	RestException(HttpStatus httpStatus, String message, Throwable ex)
	{
		this();
		this.status = httpStatus;
		this.message = message;
		this.messageDetail = ex.getLocalizedMessage();
	}
	
	private void addValidationException(String objectName, String fieldName, Object rejectedValue, String message)
	{
		if(validationExceptions == null)
			validationExceptions = new ArrayList<RestValidationException>();
			
		validationExceptions.add(new RestValidationException(objectName, fieldName, rejectedValue, message));
	}

	/** For Field Errors **/
	private void addValidationException(FieldError fieldError)
	{
		this.addValidationException(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}
	
	void addValidationException(List<FieldError> fieldErrors)
	{
		fieldErrors.forEach(this::addValidationException);
		// fieldErrors.forEach(fError -> addValidationException(fError));	// Or use this
	}
	
	/** For Object Errors **/
    private void addValidationException(ObjectError objectError) 
    {
    	this.addValidationException(objectError.getObjectName(), null, null, objectError.getDefaultMessage());
    }

    void addValidationExceptions(List<ObjectError> globalErrors) 
    {
        globalErrors.forEach(this::addValidationException);
        // globalErrors.forEach(gError -> addValidationException(gError));	// Or use this
    }
    
    /** For ConstraintVoilation Errors **/
    private void addValidationException(ConstraintViolation<?> cv)
    {
    	this.addValidationException(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }
    
    void addValidationExceptions(Set<ConstraintViolation<?>> constraintViolations)
    {
    	constraintViolations.forEach(this::addValidationException);
    	// constraintViolations.forEach(cVoilation -> addValidationException(cVoilation));	// Or use this
    }
}
