package com.elkased.todoapi.exception;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends ApiBaseExceptionHandling {

    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
