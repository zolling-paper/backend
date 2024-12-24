package com.zollingpaper.backend.global.exception;

public class PaperException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public PaperException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
