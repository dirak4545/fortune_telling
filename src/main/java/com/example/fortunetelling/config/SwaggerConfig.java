package com.example.fortunetelling.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("사주 해석 API")
                        .version("1.0")
                        .description("사용자의 사주를 기반으로 운세를 해석해주는 API 문서입니다. ChatGPT를 활용한 분석 결과를 제공합니다.")
                        .termsOfService("https://example.com/terms")
                        .contact(new Contact()
                                .name("개발자")
                                .url("https://github.com/yourusername")
                                .email("your-email@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("개발 서버"),
                        new Server()
                                .url("https://api.fortunetelling.example.com")
                                .description("운영 서버")))
                .externalDocs(new ExternalDocumentation()
                        .description("사주팔자 해석에 대한 추가 정보")
                        .url("https://example.com/docs"));
    }
}