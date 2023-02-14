package com.atos.technicaltest.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

//This class represents the body of the custom exception
class ApiException {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private Map<String, String> errors;
	
	ApiException(){
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiException(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}
	
	public ApiException(HttpStatus status, String message, Map<String, String> errors) {
		this(status, message);
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
}
