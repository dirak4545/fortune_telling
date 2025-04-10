package com.example.fortunetelling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FortuneResponseDto {
    private Long id;
    private String name;
    private LocalDateTime birthDateTime;
    private String gender;
    private String question;
    private String fortuneResult;
    private LocalDateTime createdAt;
}