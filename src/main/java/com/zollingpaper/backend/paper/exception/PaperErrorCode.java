package com.zollingpaper.backend.paper.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.zollingpaper.backend.global.exception.ErrorMessage;
import com.zollingpaper.backend.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum PaperErrorCode implements ErrorResponse {
    NOT_FOUND(BAD_REQUEST, "해당 편지가 존재하지 않습니다."),
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
