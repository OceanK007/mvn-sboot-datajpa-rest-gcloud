package com.ocean.springboot.config.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	private ResponseEntity<Object> buildResponseEntity(Object object, HttpStatus httpStatus)
	{
		return new ResponseEntity<Object>(object, httpStatus);
	}
	
	/**
	BEFORE:
	
		{
		    "timestamp": 1509444686399,
		    "status": 400,
		    "error": "Bad Request",
		    "exception": "org.springframework.web.bind.MissingServletRequestParameterException",
		    "message": "Required Long parameter 'userId' is not present",
		    "path": "/api/user/get"
		}
		
	AFTER:
	
		{
		    "timestamp": "2017-10-31 11:44:17 GMT+0000",
		    "statusCode": 400,
		    "statusType": "Bad Request",
		    "exceptionClass": "org.springframework.web.bind.MissingServletRequestParameterException",
		    "exceptionMessage": "Required Long parameter 'userId' is not present",
		    "message": "userId parameter is missing",
		    "requestType": "GET",
		    "path": "/api/user/get"
		}
	*/
	@Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        String errorMessage = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new RestException(BAD_REQUEST, ex, errorMessage, request), BAD_REQUEST);
    }
	
	/** Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well. **/
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new RestException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex, builder.substring(0, builder.length() - 2), request), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    /** Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation. **/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
    	RestException restException = new RestException(HttpStatus.BAD_REQUEST);
    	restException.setMessage("Validation Error");
    	restException.addFieldValidationExceptions(ex.getBindingResult().getFieldErrors());
    	restException.addObjectValidationExceptions(ex.getBindingResult().getGlobalErrors());
    	return buildResponseEntity(restException, HttpStatus.BAD_REQUEST);
    }
    
    /** Handle HttpMessageNotReadableException. Happens when request JSON is malformed. **/
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        String errorMessage = "Malformed JSON request";
        return buildResponseEntity(new RestException(BAD_REQUEST, ex, errorMessage, request), BAD_REQUEST);
    }
    
    /** Handle HttpMessageNotWritableException. **/
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
    	String errorMessage = "Error writing JSON output";
    	return buildResponseEntity(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ex, errorMessage, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /** This is an additional handler using @ExceptionHandler **/
    /** Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails. **/
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) 
    {
    	RestException restException = new RestException(HttpStatus.BAD_REQUEST);
    	restException.setMessage("Validation Error");
    	restException.addConstraintValidationExceptions(ex.getConstraintViolations());
    	return buildResponseEntity(restException, HttpStatus.BAD_REQUEST);
    }
    
    /** 
 	{
	    "timestamp": "2017-11-05 12:42:44 GMT+0000",
	    "statusCode": 400,
	    "statusType": "Bad Request",
	    "exceptionClass": "com.ocean.springboot.config.exception.ApplicationGenericException",
	    "exceptionMessage": "Validation error",
	    "message": "Validation error",
	    "requestType": "POST",
	    "path": "/api/user/create",
	    "validationExceptions": [
	        {
	            "fieldName": "username",
	            "message": "Username is blank"
	        }
	    ]
	}
    */
    /** This is an additional handler using @ExceptionHandler **/
    /** Handles com.ocean.springboot.config.exception.ApplicationGenericException. Explicitly thrown exception will be handled. **/
    @ExceptionHandler(ApplicationGenericException.class)
    protected ResponseEntity<Object> handleGenericException(ApplicationGenericException ex, WebRequest request) 
    {
    	RestException restException = new RestException(HttpStatus.BAD_REQUEST, ex, (ex.getMessage() != null ? ex.getMessage() : "Unexpected error"), request);
    	
    	List<RestValidationException> restValidationExceptions = new ArrayList<>();
    	ex.getExceptionMap().forEach((k,v) -> restValidationExceptions.add(new RestValidationException(null, k, null, v)));
    	
    	restException.setValidationExceptions(restValidationExceptions);
    	
    	return buildResponseEntity(restException, HttpStatus.BAD_REQUEST);
    }

    /** This is an additional handler using @ExceptionHandler **/
    /** Handle javax.persistence.EntityNotFoundException **/
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex, WebRequest request) 
    {
    	String errorMessage = "Entity not found";
    	return buildResponseEntity(new RestException(HttpStatus.NOT_FOUND, ex, errorMessage, request), HttpStatus.NOT_FOUND);
    }
    
    /** This is an additional handler using @ExceptionHandler **/
    /** Handle DataIntegrityViolationException, inspects the cause for different DB causes. **/
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) 
    {
    	if(ex.getCause() instanceof ConstraintViolationException)
    	{
    		return buildResponseEntity(new RestException(HttpStatus.CONFLICT, ex, "Database error", request), HttpStatus.CONFLICT);
    	}
    	return buildResponseEntity(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ex, "Unexpected error", request), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /** This is an additional handler using @ExceptionHandler **/
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) 
    {
    	String errorMessage = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
    	return buildResponseEntity(new RestException(HttpStatus.BAD_REQUEST, ex, errorMessage, request), HttpStatus.BAD_REQUEST);
    }
}