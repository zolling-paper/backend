package com.zollingpaper.backend.global.config.swagger;

import java.util.List;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public OpenAPI openAPI() {
        List<Server> servers = swaggerProperties.hosts().stream()
                .map(server -> new Server().url(server))
                .toList();

        return new OpenAPI()
                .components(new Components())
                .info(info())
                .servers(servers);
    }

    private Info info() {
        return new Info()
                .title("🎓 졸링페이퍼 API")
                .description("졸링페이퍼 API 명세서입니다.")
                .version("1.0");
    }
}
