package com.alkemy.ong.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ExceptionMessage returnError(Exception e) {
		return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());

	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ExceptionMessage returnErrorBadRequest(NotFoundException e) {
		return new ExceptionMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ExceptionMessage returnErrorIllegalArgument(IllegalArgumentException e) {
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ExceptionMessage returnErrorMethodArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(),"bad request", LocalDateTime.now(), errors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailExistException.class)
	@ResponseBody
	public ExceptionMessage handleEmailExist(HttpServletRequest request,
													 EmailExistException e) {

		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());

	}


	
	
}
