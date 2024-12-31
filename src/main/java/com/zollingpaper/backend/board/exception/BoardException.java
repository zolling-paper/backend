package com.zollingpaper.backend.board.exception;

import com.zollingpaper.backend.global.exception.ErrorResponse;
import com.zollingpaper.backend.global.exception.GlobalCustomException;

public class BoardException extends GlobalCustomException {
    public BoardException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
