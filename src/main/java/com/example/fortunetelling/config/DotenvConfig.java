package com.example.fortunetelling.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev") // 개발 환경에서만 활성화
public class DotenvConfig {

    @PostConstruct
    public void loadEnv() {
        // .env 파일이 있으면 환경 변수로 로드
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // 환경 변수가 설정되어 있지 않은 경우에만 .env 파일에서 가져온 값으로 설정
        setEnvIfNotExists("DB_URL", dotenv);
        setEnvIfNotExists("DB_USERNAME", dotenv);
        setEnvIfNotExists("DB_PASSWORD", dotenv);
        setEnvIfNotExists("CHATGPT_API_KEY", dotenv);
    }

    private void setEnvIfNotExists(String key, Dotenv dotenv) {
        if (System.getenv(key) == null && dotenv.get(key) != null) {
            System.setProperty(key, dotenv.get(key));
        }
    }
}