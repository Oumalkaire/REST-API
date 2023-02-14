package com.atos.technicaltest.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.atos.technicaltest.utils.Messages;


@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {CustomException.class})
	public ResponseEntity<ApiException> handleUserNotFoundException(CustomException ex){
		return ResponseEntity.status(ex.status).body(new ApiException(ex.status, ex.getMessage()));
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiException handleInvalidArgument(MethodArgumentNotValidException ex) {
		
		Map<String,String> map = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		
		return new ApiException(HttpStatus.BAD_REQUEST, Messages.getMessage("user.data.not.valid"), map);
	}
	
	//Method to cover remaining cases
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiException handleException(Exception ex){
		return new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
}
