package com.zollingpaper.backend.auth.exception;

import com.zollingpaper.backend.global.exception.ErrorResponse;
import com.zollingpaper.backend.global.exception.GlobalCustomException;

public class AuthException extends GlobalCustomException {
    public AuthException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
