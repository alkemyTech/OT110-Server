package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class EmptyDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EmptyDataException(String s) {
		super(s);
	}
	
}
