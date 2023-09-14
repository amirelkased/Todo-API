package com.elkased.todoapi.exception;

import org.springframework.http.HttpStatus;

public class LoginFailureException extends ApiBaseExceptionHandling {

    public LoginFailureException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
