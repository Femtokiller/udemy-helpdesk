package com.udemy.helpdesk.services.exceptions;

public class MethodArgumentNotValidException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MethodArgumentNotValidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MethodArgumentNotValidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
