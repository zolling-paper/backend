package com.zollingpaper.backend.board.exception;

import com.zollingpaper.backend.global.exception.ErrorMessage;
import com.zollingpaper.backend.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum BoardErrorCode implements ErrorResponse {
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 보드가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    BoardErrorCode(HttpStatus httpStatus, String message) {
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
