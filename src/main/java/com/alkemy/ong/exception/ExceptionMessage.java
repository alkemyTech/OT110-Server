package com.alkemy.ong.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionMessage {

	private int code;
	private String message;
	private LocalDateTime timestamp;
	private Map<String, String> errors;
	
	
	
	public ExceptionMessage(int code, String message, LocalDateTime timestamp) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}

	public ExceptionMessage(int code, String message, LocalDateTime timestamp, Map<String, String> errors) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}
	
}
