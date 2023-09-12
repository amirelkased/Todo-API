package com.elkased.todoapi.exception;

import com.elkased.todoapi.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> apiExceptionHandler(ApiBaseExceptionHandling ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setMessage(ex.getMessage());
        errorDTO.setPath(request.getDescription(false));

        return new ResponseEntity<>(errorDTO, ex.getStatusCode());
    }
}
