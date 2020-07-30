package com.example.office.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(ManagerIdNotFoundException.class)
	public ResponseEntity<?> ManagerNotFoundHandling(ManagerIdNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails("false",new Date(), exception.getMessage(), request.getDescription(false),404);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handling global exception
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		
		ErrorDetails errorDetails = 
				new ErrorDetails("false",new Date(), exception.getMessage(), request.getDescription(false),500);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
