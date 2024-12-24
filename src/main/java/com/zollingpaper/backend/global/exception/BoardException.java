package com.zollingpaper.backend.global.exception;

public class BoardException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public BoardException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
