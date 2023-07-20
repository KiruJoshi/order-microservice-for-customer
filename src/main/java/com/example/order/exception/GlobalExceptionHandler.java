package com.example.order.exception;



import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.order.config.IdNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadExceptionMethod(BadRequestException ex){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("timestamp", new Date());
		List<String> errors= Arrays.asList("id is not correct");
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("message", errors);
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(body);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundMethod(IdNotFoundException ex){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("timestamp", new Date());
		String errors= "id is not correct/found";
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("message", errors);
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(body);
	}
	
	@ExceptionHandler(SqlProblemException.class)
	public ResponseEntity<Object> handleSqlProblemException(SqlProblemException ex){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("timestamp", new Date());
		String errors= "problem is in database";
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.put("message", errors);
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(body);
	}
	
}
