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
		this.timestamp = DateTimeHelper.formatDateTime(DateTime.now(DateTimeZone.UTC), DateTimeConstant.DATE_FORMAT_TIMEZONE.getValue());
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
	private void addFieldValidationException(FieldError fieldError)
	{
		this.addValidationException(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}
	
	void addFieldValidationExceptions(List<FieldError> fieldErrors)
	{
		fieldErrors.forEach(this::addFieldValidationException);
		// fieldErrors.forEach(fError -> addFieldValidationException(fError));	// Or use this
	}
	
	/** For Object Errors **/
    private void addObjectValidationException(ObjectError objectError) 
    {
    	this.addValidationException(objectError.getObjectName(), null, null, objectError.getDefaultMessage());
    }

    void addObjectValidationExceptions(List<ObjectError> globalErrors) 
    {
        globalErrors.forEach(this::addObjectValidationException);
        // globalErrors.forEach(gError -> addObjectValidationException(gError));	// Or use this
    }
    
    /** For ConstraintVoilation Errors **/
    private void addConstraintValidationException(ConstraintViolation<?> cv)
    {
    	this.addValidationException(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }
    
    void addConstraintValidationExceptions(Set<ConstraintViolation<?>> constraintViolations)
    {
    	constraintViolations.forEach(this::addConstraintValidationException);
    	// constraintViolations.forEach(cVoilation -> addConstraintValidationException(cVoilation));	// Or use this
    }

    /** Additional methods **/
	private void addValidationException(String objectName, String fieldName, Object rejectedValue, String message)
	{
		if(validationExceptions == null)
			validationExceptions = new ArrayList<RestValidationException>();
			
		validationExceptions.add(new RestValidationException(objectName, fieldName, rejectedValue, message));
	}

    /** Getters and setters **/
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpMethod getRequestType() {
		return requestType;
	}

	public void setRequestType(HttpMethod requestType) {
		this.requestType = requestType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RestValidationException> getValidationExceptions() {
		return validationExceptions;
	}

	public void setValidationExceptions(List<RestValidationException> validationExceptions) {
		this.validationExceptions = validationExceptions;
	}
}
