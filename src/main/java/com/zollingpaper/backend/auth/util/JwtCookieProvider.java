package com.zollingpaper.backend.auth.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class JwtCookieProvider {

    private static final String COOKIE_NAME = "token";
    private static final String DOMAIN = ".zolling.me";
    private static final int COOKIE_MAX_AGE = 1800;

    public static void setTokenCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, token)
                .maxAge(COOKIE_MAX_AGE)
                .httpOnly(true)
                .secure(true)
                .domain(DOMAIN)
                .path("/")
                .sameSite("None")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
