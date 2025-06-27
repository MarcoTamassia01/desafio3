package com.desafio3.crud.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio3.crud.dtos.ResponseErrorDTO;
import com.desafio3.crud.dtos.ValidationError;
import com.desafio3.crud.services.exceptions.DataBaseException;
import com.desafio3.crud.services.exceptions.RequiredObjectIsNullException;
import com.desafio3.crud.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ResponseErrorDTO>resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
			HttpStatus status = HttpStatus.NOT_FOUND;
			ResponseErrorDTO err = new ResponseErrorDTO(exception.getMessage(), status.value(), Instant.now(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		}

	@ExceptionHandler(RequiredObjectIsNullException.class)
		public ResponseEntity<ResponseErrorDTO>requiredObjectIsNull(RequiredObjectIsNullException exception,HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseErrorDTO err = new ResponseErrorDTO(exception.getMessage(), status.value(), Instant.now(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
		public ResponseEntity<ResponseErrorDTO>dataBase(DataBaseException exception,HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseErrorDTO err = new ResponseErrorDTO(exception.getMessage(), status.value(), Instant.now(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseErrorDTO>methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError("Dados invalidos!!!", status.value(), Instant.now(), request.getRequestURI());
		for(FieldError f:exception.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
}
	

