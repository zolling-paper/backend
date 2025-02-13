package com.zollingpaper.backend.auth.config;

import com.zollingpaper.backend.auth.service.AuthService;
import com.zollingpaper.backend.auth.util.JwtCookieConsumer;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthWebMvcConfig implements WebMvcConfigurer {

    private final AuthService authService;
    private final JwtCookieConsumer jwtCookieConsumer;

    public AuthWebMvcConfig(AuthService authService, JwtCookieConsumer jwtCookieConsumer) {
        this.authService = authService;
        this.jwtCookieConsumer = jwtCookieConsumer;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AccessorArgumentResolver(authService, jwtCookieConsumer));
    }
}
