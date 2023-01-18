package com.example.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		      String msg=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
	Map<String,String> mp=new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldname=((FieldError) error).getField();
		String message=error.getDefaultMessage();
		mp.put(fieldname, message);
	});
	
	
		return new ResponseEntity<Map<String,String>>(mp,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		      String msg=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.BAD_REQUEST);
	}
	
}
