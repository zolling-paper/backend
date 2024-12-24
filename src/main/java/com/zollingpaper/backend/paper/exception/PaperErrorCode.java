package com.zollingpaper.backend.paper.exception;

import com.zollingpaper.backend.global.exception.ErrorMessage;
import com.zollingpaper.backend.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum PaperErrorCode implements ErrorResponse {
    ;

    private final HttpStatus httpStatus;
    private final String message;

    PaperErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(this.message);
    }
}
