package com.global.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundException(NotFoundException exception){
		ExceptionAttribute message = new ExceptionAttribute(exception.getLocalizedMessage(),Arrays.asList(exception.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> argumentNotFoundException(MethodArgumentNotValidException exception){
		BindingResult binding = exception.getBindingResult();
		Map<String,String> errorAttribute = new HashMap<>();
		for(FieldError error : binding.getFieldErrors()) {
			errorAttribute.put(error.getField(),error.getDefaultMessage());
		}
		ExceptionAttribute exceptionAttribute = new ExceptionAttribute(errorAttribute);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exceptionAttribute);
	}
	@ExceptionHandler(ExpiryRefreshTokenException.class)
	public ResponseEntity<?> expiryRefreshTokenException(ExpiryRefreshTokenException exception){
		ExceptionAttribute message = new ExceptionAttribute(exception.getLocalizedMessage(),Arrays.asList(exception.getMessage()));
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
	}

}
