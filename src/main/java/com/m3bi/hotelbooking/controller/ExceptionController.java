package com.m3bi.hotelbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.CustomError;
import com.m3bi.hotelbooking.model.CustomResponse;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class ExceptionController {
	 @ExceptionHandler(ServletRequestBindingException.class)
	    public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) 
	    {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        CustomError error = new CustomError(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
	        CustomResponse response = new CustomResponse(false, error, null);
	        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	    }
	 
	    @ExceptionHandler(CustomException.class)
	    public final ResponseEntity<Object> handleCustomExceptions(Exception ex, WebRequest request) 
	    {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        CustomError error = new CustomError(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
	        CustomResponse response = new CustomResponse(false, error, null);
	        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(AuthenticationException.class)
	    public final ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) 
	    {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        CustomError error = new CustomError(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString());
	        CustomResponse response = new CustomResponse(false, error, null);
	        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) 
	    {
	    	ex.printStackTrace();
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        CustomError error = new CustomError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
	        CustomResponse response = new CustomResponse(false, error, null);
	        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
