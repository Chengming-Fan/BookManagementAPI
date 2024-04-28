package com.fan.bookmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(BusinessException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build()
            );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build()
            );
    }

}
