package com.zollingpaper.backend.auth.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthWebMvcConfig implements WebMvcConfigurer {

    private final AccessorArgumentResolver accessorArgumentResolver;

    public AuthWebMvcConfig(AccessorArgumentResolver accessorArgumentResolver) {
        this.accessorArgumentResolver = accessorArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accessorArgumentResolver);
    }
}
