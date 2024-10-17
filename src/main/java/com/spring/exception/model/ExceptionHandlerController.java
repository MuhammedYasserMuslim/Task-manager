package com.spring.exception.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler
    public ResponseEntity<?> handleException(BaseException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ExceptionResponse(exception.getMessage(),exception.getStatus() ));
    }
}
