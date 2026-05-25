package com.udemy.helpdesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import com.udemy.helpdesk.utils.DateUtils;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request)
	{		
		StandardError error = new StandardError(DateUtils.dataAtualFormatado(DateUtils.DD_MM_YYYY_HH_MM_SS), HttpStatus.NOT_FOUND.value(), "Object Not Found", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request)
	{		
		StandardError error = new StandardError(DateUtils.dataAtualFormatado(DateUtils.DD_MM_YYYY_HH_MM_SS), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException exception, HttpServletRequest request)
	{		
		ValidationError validationErrors = new ValidationError(DateUtils.dataAtualFormatado(DateUtils.DD_MM_YYYY_HH_MM_SS), HttpStatus.BAD_REQUEST.value(), "Validation Error", "Erro na validação dos campos", request.getRequestURI());
		
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) 
		{
			validationErrors.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}
}
