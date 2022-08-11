package com.kimsongnam.mindset.exception;

import com.kimsongnam.mindset.exception.handler.ApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
    private static final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

    public ForbiddenException(String message) {
        super(httpStatus, message);
    }
}
