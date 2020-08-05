package com.yaswanth.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalExceptionHandlerMethods {
    
	
   // @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handleGenericException(Exception e) {
		System.out.println("UnKnown Exception Occured : " + e);

		return "GenericException";
	}
}
