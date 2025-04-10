package com.example.fortunetelling.dto;

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
public class FortuneRequestDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    @NotNull(message = "생년월일은 필수 입력값입니다")
    @Past(message = "생년월일은 과거 날짜여야 합니다")
    private LocalDateTime birthDateTime;

    @NotBlank(message = "성별은 필수 입력값입니다")
    @Pattern(regexp = "^[남여]$", message = "성별은 '남' 또는 '여'로 입력해주세요")
    private String gender;

    @Size(max = 500, message = "질문은 최대 500자까지 입력 가능합니다")
    private String question;
}