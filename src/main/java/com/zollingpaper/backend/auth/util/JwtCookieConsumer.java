package com.zollingpaper.backend.auth.util;

import com.zollingpaper.backend.auth.exception.AuthErrorCode;
import com.zollingpaper.backend.auth.exception.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class JwtCookieConsumer {

    private static final String COOKIE_NAME = "token";

    private final JwtTokenProvider jwtTokenProvider;

    public JwtCookieConsumer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String extractToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthException(AuthErrorCode.COOKIE_NOT_EXIST);
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_COOKIE));
    }

    public void validateTokenFromCookies(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            throw new RuntimeException("쿠키에 유효한 JWT 토큰이 없습니다.");
        }
    }
}
