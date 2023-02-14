package com.atos.technicaltest.exception;

import org.springframework.http.HttpStatus;

public class CustomException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	HttpStatus status;
	public CustomException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
