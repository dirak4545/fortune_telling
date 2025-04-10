package com.example.fortunetelling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사주 해석 응답 DTO")
public class FortuneResponseDto {
    @Schema(description = "사주 해석 요청 ID", example = "1")
    private Long id;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(
            description = "생년월일 및 태어난 시간",
            example = "1990-05-15T09:30:00",
            type = "string",
            format = "date-time"
    )
    private LocalDateTime birthDateTime;

    @Schema(description = "성별", example = "남", allowableValues = {"남", "여"})
    private String gender;

    @Schema(description = "해석을 원했던 질문", example = "올해 취업 운은 어떤가요?")
    private String question;

    @Schema(
            description = "ChatGPT가 분석한 사주 해석 결과",
            example = "귀하의 사주를 분석한 결과, 올해는 취업운이 매우 좋은 해입니다. 특히 6월부터 9월 사이에 좋은 기회가 있을 것으로 보입니다..."
    )
    private String fortuneResult;

    @Schema(
            description = "사주 해석 요청 생성 시간",
            example = "2025-04-10T14:30:00",
            type = "string",
            format = "date-time"
    )
    private LocalDateTime createdAt;
}