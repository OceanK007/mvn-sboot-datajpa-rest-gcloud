package com.ocean.springboot.config.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ocean.springboot.util.constant.DateTimeConstant;
import com.ocean.springboot.util.helper.DateTimeHelper;

@JsonInclude(Include.NON_NULL)	// To remove null fields from json
public class RestException 
{
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String timestamp;			// Timing
	private int statusCode;				// For error code like 404, 500, etc
	private String statusType;			// For exception type. Like "Bad Request"
	private String exceptionClass;		// Exception with class name. Like org.springframework.http.converter.HttpMessageNotReadableException
	private String exceptionMessage;	// Exception localized message
	private String message;				// Provided message
	private HttpMethod requestType;		// Request type. Like GET, POST
	private String path;				// API Url
	private List<RestValidationException> validationExceptions;

	/** Constructors **/
	RestException()
	{
		timestamp = DateTimeHelper.formatDateTime(DateTime.now(DateTimeZone.UTC), DateTimeConstant.DATE_FORMAT_TIMEZONE.getValue());
	}

	RestException(HttpStatus httpStatus)
	{
		this();
		this.statusCode = httpStatus.value();
		this.statusType = httpStatus.getReasonPhrase();
	}
	
	RestException(HttpStatus httpStatus, Throwable ex)
	{
        this(httpStatus);
        this.exceptionClass = ex.getClass().getName();
        this.exceptionMessage = ex.getLocalizedMessage();
	}
	
	RestException(HttpStatus httpStatus, Throwable ex, String message)
	{
		this(httpStatus, ex);
		this.message = message;
	}
	
	RestException(HttpStatus httpStatus, Throwable ex, String message, WebRequest webRequest)
	{
		this(httpStatus, ex, message);
		
		ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
		this.requestType = servletWebRequest.getHttpMethod();
		this.path = servletWebRequest.getRequest().getServletPath();
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

    /** Additional methods **/
	private void addValidationException(String objectName, String fieldName, Object rejectedValue, String message)
	{
		if(validationExceptions == null)
			validationExceptions = new ArrayList<RestValidationException>();
			
		validationExceptions.add(new RestValidationException(objectName, fieldName, rejectedValue, message));
	}
	
    /** Getters **/
	public String getTimestamp() {
		return timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusType() {
		return statusType;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getMessage() {
		return message;
	}

	public HttpMethod getRequestType() {
		return requestType;
	}

	public String getPath() {
		return path;
	}

	public List<RestValidationException> getValidationExceptions() {
		return validationExceptions;
	}
}
