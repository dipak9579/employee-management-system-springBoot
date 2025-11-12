package com.dipak.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>>handleRuntimeException(RuntimeException ex){
		Map<String,Object>error=new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("error", ex.getMessage());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<Map<String,Object>>handleExistsResource(ResourceAlreadyExistsException ex){
		Map<String,Object>error=new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("error", ex.getMessage());
		error.put("status", HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}

}
