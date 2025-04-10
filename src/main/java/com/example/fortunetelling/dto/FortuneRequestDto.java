package com.example.fortunetelling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사주 해석 요청 DTO")
public class FortuneRequestDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @NotNull(message = "생년월일은 필수 입력값입니다")
    @Past(message = "생년월일은 과거 날짜여야 합니다")
    @Schema(
            description = "생년월일 및 태어난 시간",
            example = "1990-05-15T09:30:00",
            required = true,
            type = "string",
            format = "date-time"
    )
    private LocalDateTime birthDateTime;

    @NotBlank(message = "성별은 필수 입력값입니다")
    @Pattern(regexp = "^[남여]$", message = "성별은 '남' 또는 '여'로 입력해주세요")
    @Schema(description = "성별 (남/여)", example = "남", required = true, allowableValues = {"남", "여"})
    private String gender;

    @Size(max = 500, message = "질문은 최대 500자까지 입력 가능합니다")
    @Schema(
            description = "해석을 원하는 구체적인 질문 (선택사항)",
            example = "올해 취업 운은 어떤가요?",
            required = false
    )
    private String question;
}