package com.kimsongnam.mindset.exception;

import com.kimsongnam.mindset.exception.handler.ApiException;
import org.springframework.http.HttpStatus;

public class BadRequesetException extends ApiException {
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BadRequesetException(String message) {
        super(httpStatus, message);
    }
}
