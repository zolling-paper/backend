package com.zollingpaper.backend.auth.config;

import com.zollingpaper.backend.auth.util.JwtCookieConsumer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtCookieConsumer jwtCookieConsumer;

    public AuthInterceptor(JwtCookieConsumer jwtCookieConsumer) {
        this.jwtCookieConsumer = jwtCookieConsumer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        jwtCookieConsumer.validateTokenFromCookies(request);
        return true;
    }
}
