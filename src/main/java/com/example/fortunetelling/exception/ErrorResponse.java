package com.example.fortunetelling.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "API 에러 응답")
public class ErrorResponse {
    @Schema(description = "HTTP 상태 코드", example = "400")
    private int status;

    @Schema(description = "에러 메시지", example = "입력값 검증 실패")
    private String message;

    @Schema(description = "필드별 에러 상세 내용", example = "{\"name\": \"이름은 필수 입력값입니다\", \"gender\": \"성별은 '남' 또는 '여'로 입력해주세요\"}")
    private Map<String, String> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = new HashMap<>();
    }
}