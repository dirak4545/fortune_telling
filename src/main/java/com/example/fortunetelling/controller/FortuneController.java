package com.example.fortunetelling.controller;

import com.example.fortunetelling.dto.FortuneRequestDto;
import com.example.fortunetelling.dto.FortuneResponseDto;
import com.example.fortunetelling.service.FortuneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fortune")
@RequiredArgsConstructor
@Tag(name = "Fortune API", description = "사주 해석 API")
public class FortuneController {
    private final FortuneService fortuneService;

    @PostMapping
    @Operation(summary = "사주 해석 요청", description = "사용자 정보와 질문을 바탕으로 사주를 해석합니다.")
    public ResponseEntity<FortuneResponseDto> createFortuneRequest(
            @Valid @RequestBody FortuneRequestDto requestDto) {
        FortuneResponseDto responseDto = fortuneService.createFortuneRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "사주 해석 결과 조회", description = "ID로 사주 해석 결과를 조회합니다.")
    public ResponseEntity<FortuneResponseDto> getFortuneRequest(
            @Parameter(description = "사주 해석 요청 ID") @PathVariable Long id) {
        FortuneResponseDto responseDto = fortuneService.getFortuneRequestById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @Operation(summary = "모든 사주 해석 결과 조회", description = "모든 사주 해석 결과를 조회합니다.")
    public ResponseEntity<List<FortuneResponseDto>> getAllFortuneRequests() {
        List<FortuneResponseDto> responseDtos = fortuneService.getAllFortuneRequests();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/search")
    @Operation(summary = "이름으로 사주 해석 결과 검색", description = "이름으로 사주 해석 결과를 검색합니다.")
    public ResponseEntity<List<FortuneResponseDto>> getFortuneRequestsByName(
            @Parameter(description = "검색할 이름") @RequestParam String name) {
        List<FortuneResponseDto> responseDtos = fortuneService.getFortuneRequestsByName(name);
        return ResponseEntity.ok(responseDtos);
    }
}