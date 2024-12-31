package com.zollingpaper.backend.paper.exception;

import com.zollingpaper.backend.global.exception.ErrorResponse;

public class PaperException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public PaperException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
