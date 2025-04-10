//package com.example.fortunetelling.config;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.core.env.ConfigurableEnvironment;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.MutablePropertySources;
//import org.springframework.core.env.PropertiesPropertySource;
//
//import java.util.Properties;
//
//@Configuration
//@Profile("dev")
//public class DotenvConfig {
//
//    private final Environment env;
//
//    public DotenvConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @PostConstruct
//    public void loadEnv() {
//        // .env 파일이 있으면 환경 변수로 로드
//        Dotenv dotenv = Dotenv.configure()
//                .ignoreIfMissing()
//                .load();
//
//        Properties props = new Properties();
//
//        // 환경 변수가 설정되어 있지 않은 경우에만 .env 파일에서 가져온 값으로 설정
//        setEnvIfNotExists("DB_URL", dotenv, props);
//        setEnvIfNotExists("DB_USERNAME", dotenv, props);
//        setEnvIfNotExists("DB_PASSWORD", dotenv, props);
//        setEnvIfNotExists("CHATGPT_API_KEY", dotenv, props);
//
//        // 환경 변수를 Spring 환경에 추가
//        if (!props.isEmpty() && env instanceof ConfigurableEnvironment) {
//            MutablePropertySources sources = ((ConfigurableEnvironment) env).getPropertySources();
//            sources.addFirst(new PropertiesPropertySource("dotenv", props));
//        }
//    }
//
//    private void setEnvIfNotExists(String key, Dotenv dotenv, Properties props) {
//        if (env.getProperty(key) == null && dotenv.get(key) != null) {
//            props.setProperty(key, dotenv.get(key));
//        }
//    }
//}