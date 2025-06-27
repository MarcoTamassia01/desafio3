package com.desafio3.crud.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class ValidationError extends ResponseErrorDTO {
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(String error, Integer status, Instant timeStamp, String path) {
	super(error, status, timeStamp, path);
}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
