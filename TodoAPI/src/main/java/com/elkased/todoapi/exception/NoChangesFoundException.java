package com.elkased.todoapi.exception;

import org.springframework.http.HttpStatus;

public class NoChangesFoundException extends ApiBaseExceptionHandling {

    public NoChangesFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
