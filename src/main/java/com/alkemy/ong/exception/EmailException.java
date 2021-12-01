package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailException extends Exception{

    private String message;
    private HttpStatus status;

}