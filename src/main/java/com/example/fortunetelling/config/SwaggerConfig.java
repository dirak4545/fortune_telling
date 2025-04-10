package com.example.fortunetelling.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("사주 해석 API")
                        .version("1.0")
                        .description("사용자의 사주를 해석해주는 API 문서입니다.")
                        .contact(new Contact()
                                .name("개발자")
                                .email("your-email@example.com")));
    }
}