package com.example.fortunetelling.service;

import com.example.fortunetelling.dto.FortuneRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private final RestTemplate restTemplate;

    @Value("${chatgpt.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    public String getFortuneTelling(FortuneRequestDto requestDto) {
        try {
            // ChatGPT API 요청 객체 생성
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-4"); // 또는 다른 모델
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 1000);

            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", "당신은 사주팔자를 분석하는 전문가입니다. 제공된 정보를 바탕으로 사주 해석을 해주세요. " +
                    "운세를 상세하게 설명하고, 이를 토대로 앞으로의 운세와 행운의 방향을 제시해주세요.");

            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", String.format(
                    "이름: %s, 성별: %s, 생년월일: %s, 질문: %s",
                    requestDto.getName(),
                    requestDto.getGender(),
                    requestDto.getBirthDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                    requestDto.getQuestion() != null ? requestDto.getQuestion() : "전반적인 운세를 알려주세요."
            ));

            messages.add(systemMessage);
            messages.add(userMessage);
            requestBody.put("messages", messages);

            // API 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            // API 요청
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

            // 응답 처리
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, String> message = (Map<String, String>) choice.get("message");
                    return message.get("content");
                }
            }

            return "사주 해석을 가져오는 중 오류가 발생했습니다.";
        } catch (Exception e) {
            return "사주 해석을 가져오는 중 오류가 발생했습니다: " + e.getMessage();
        }
    }
}