package com.zollingpaper.backend.auth.exception;

import com.zollingpaper.backend.global.exception.ErrorMessage;
import com.zollingpaper.backend.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorResponse {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_COOKIE(HttpStatus.UNAUTHORIZED, "유효하지 않은 쿠키입니다."),
    COOKIE_NOT_EXIST(HttpStatus.UNAUTHORIZED, "쿠키가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    AuthErrorCode(HttpStatus httpStatus, String message) {
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
