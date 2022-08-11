package com.kimsongnam.mindset.exception;

import com.kimsongnam.mindset.exception.handler.ApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(httpStatus, message);
    }
}
