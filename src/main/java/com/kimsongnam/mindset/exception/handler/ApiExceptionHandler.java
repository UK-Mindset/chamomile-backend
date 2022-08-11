package com.kimsongnam.mindset.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> ApiException(final ApiException e){
        HttpStatus httpStatus = e.getHttpStatus();

        return new ResponseEntity<>(new ErrorResponse(e.getHttpStatus(), e.getMessage()), httpStatus);
    }
}