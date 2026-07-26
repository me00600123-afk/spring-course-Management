package com.global.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundException(NotFoundException exception){
		ExceptionAttribute message = new ExceptionAttribute(exception.getLocalizedMessage(),Arrays.asList(exception.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

}
