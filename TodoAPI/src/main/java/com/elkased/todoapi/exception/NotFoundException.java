package com.elkased.todoapi.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseExceptionHandling {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
