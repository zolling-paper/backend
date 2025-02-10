package com.zollingpaper.backend.global.config.swagger;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
public record SwaggerProperties(List<String> hosts) {
}
