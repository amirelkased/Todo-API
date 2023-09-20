package com.elkased.todoapi.exception;

import org.springframework.http.HttpStatus;

public class RegisterFailureException extends ApiBaseExceptionHandling {

    public RegisterFailureException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
