package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailException extends Exception{

    private static final long serialVersionUID = 1L;
    private String message;
    private HttpStatus status;

}