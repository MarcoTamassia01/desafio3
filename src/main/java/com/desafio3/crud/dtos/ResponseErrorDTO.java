package com.desafio3.crud.dtos;

import java.time.Instant;

public class ResponseErrorDTO {
	
	private String error;
	private Integer status;
	private Instant timeStamp;
	private String path;
	
	public ResponseErrorDTO() {
	}

	public ResponseErrorDTO(String error, Integer status, Instant timeStamp, String path) {
		this.error = error;
		this.status = status;
		this.timeStamp = timeStamp;
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public Integer getStatus() {
		return status;
	}

	public Instant getTimeStamp() {
		return timeStamp;
	}

	public String getPath() {
		return path;
	}
	

}
