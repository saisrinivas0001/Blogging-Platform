package com.blogging_platform.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, 
															HttpServletRequest request){
		ErrorResponse error = new ErrorResponse(
												LocalDateTime.now(),
												HttpStatus.NOT_FOUND.value(),
												ex.getMessage(),
												request.getRequestURI()
												);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExist(UserAlreadyExistException ex,
																HttpServletRequest request){
		ErrorResponse error = new ErrorResponse(
												LocalDateTime.now(),
												HttpStatus.CONFLICT.value(),
												ex.getMessage(),
												request.getRequestURI()
												);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BlogNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBlogNotFound(BlogNotFoundException ex,
															HttpServletRequest request
															){
		ErrorResponse error = new ErrorResponse(
												LocalDateTime.now(),
												HttpStatus.NOT_FOUND.value(),
												ex.getMessage(),
												request.getRequestURI()
												);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request){
		ErrorResponse error = new ErrorResponse(
												LocalDateTime.now(),
												HttpStatus.INTERNAL_SERVER_ERROR.value(),
												ex.getMessage(),
												request.getRequestURI()
												);
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
