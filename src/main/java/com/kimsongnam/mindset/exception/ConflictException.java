package com.kimsongnam.mindset.exception;

import com.kimsongnam.mindset.exception.handler.ApiException;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {
    private static final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public ConflictException(String message) {
        super(httpStatus, message);
    }
}
