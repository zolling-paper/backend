package com.zollingpaper.backend.auth.config;

import com.zollingpaper.backend.auth.dto.Accessor;
import com.zollingpaper.backend.auth.exception.AuthErrorCode;
import com.zollingpaper.backend.auth.exception.AuthException;
import com.zollingpaper.backend.auth.service.AuthService;
import com.zollingpaper.backend.auth.util.JwtCookieConsumer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AccessorArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthService authService;
    private final JwtCookieConsumer jwtCookieConsumer;

    public AccessorArgumentResolver(AuthService authService, JwtCookieConsumer jwtCookieConsumer) {
        this.authService = authService;
        this.jwtCookieConsumer = jwtCookieConsumer;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.hasParameterAnnotation(Authenticated.class);
        boolean isAccessorClass = Accessor.class.isAssignableFrom(parameter.getParameterType());
        return hasAuthAnnotation && isAccessorClass;
    }

    @Override
    public Accessor resolveArgument(MethodParameter parameter,
                                    ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest,
                                    WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String accessAddress = jwtCookieConsumer.extractSubject(request);
        if (!authService.isBoardExistByAccessAddress(accessAddress)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
        return new Accessor(accessAddress);
    }
}
