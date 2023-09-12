package com.elkased.todoapi.exception;


import org.springframework.http.HttpStatus;

public abstract class ApiBaseExceptionHandling extends RuntimeException {

    protected ApiBaseExceptionHandling(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
