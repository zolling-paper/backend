package com.zollingpaper.backend.board.exception;

import com.zollingpaper.backend.global.exception.ErrorResponse;

public class BoardException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public BoardException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
