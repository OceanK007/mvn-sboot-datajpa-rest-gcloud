package com.ocean.springboot.config.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
    
    /** This is an additional handler using @ExceptionHandler **/
    /** Handles com.ocean.springboot.config.exception.RestExceptionHandler. Thrown when explicit validation check fails. **/
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, HttpStatus status) 
    {
    	ex.setMessage("Validation Error");
    	ex.setStatusCode(HttpStatus.BAD_REQUEST.value());
    	ex.setStatusType(HttpStatus.BAD_REQUEST.getReasonPhrase());
    	return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }
}
