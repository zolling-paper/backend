package com.zollingpaper.backend.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handle(GlobalCustomException e) {
        ErrorResponse response = e.getErrorResponse();
        return ResponseEntity.status(response.getHttpStatus())
                .body(response.getErrorMessage());
    }
}
