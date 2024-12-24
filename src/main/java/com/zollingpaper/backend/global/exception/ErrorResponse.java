package com.zollingpaper.backend.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {

    HttpStatus getHttpStatus();

    ErrorMessage getErrorMessage();
}
