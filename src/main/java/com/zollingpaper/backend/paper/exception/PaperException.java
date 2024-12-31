package com.zollingpaper.backend.paper.exception;

import com.zollingpaper.backend.global.exception.ErrorResponse;
import com.zollingpaper.backend.global.exception.GlobalCustomException;

public class PaperException extends GlobalCustomException {
    public PaperException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
