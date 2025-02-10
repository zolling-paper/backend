package com.zollingpaper.backend.auth.util;

import com.zollingpaper.backend.auth.exception.AuthErrorCode;
import com.zollingpaper.backend.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenValidator {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public static void checkToken(HttpServletRequest request, JwtTokenProvider jwtTokenProvider) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }

        String token = authHeader.substring(BEARER_PREFIX.length());

        if (!jwtTokenProvider.validateToken(token)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}
