package com.desafio3.crud.services.exceptions;

public class RequiredObjectIsNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RequiredObjectIsNullException(String msg) {
		super(msg);
	}

}
