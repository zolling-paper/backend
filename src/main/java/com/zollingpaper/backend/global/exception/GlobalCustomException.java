package com.zollingpaper.backend.global.exception;

public class GlobalCustomException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public GlobalCustomException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
