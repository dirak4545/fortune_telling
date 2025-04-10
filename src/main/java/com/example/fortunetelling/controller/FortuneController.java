package com.example.fortunetelling.controller;

import com.example.fortunetelling.dto.FortuneRequestDto;
import com.example.fortunetelling.dto.FortuneResponseDto;
import com.example.fortunetelling.exception.ErrorResponse;
import com.example.fortunetelling.service.FortuneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fortune")
@RequiredArgsConstructor
@Tag(name = "Fortune API", description = "사주 해석 서비스 API")
public class FortuneController {
    private final FortuneService fortuneService;

    @PostMapping
    @Operation(
            summary = "사주 해석 요청",
            description = "사용자의 이름, 생년월일, 성별, 질문을 바탕으로 ChatGPT를 활용한 사주를 해석 결과를 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "사주 해석 성공",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FortuneResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 오류",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<FortuneResponseDto> createFortuneRequest(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사주 해석 요청 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FortuneRequestDto.class))
            )
            @Valid @RequestBody FortuneRequestDto requestDto) {
        FortuneResponseDto responseDto = fortuneService.createFortuneRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "사주 해석 결과 조회",
            description = "요청 ID를 기반으로 특정 사주 해석 결과를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FortuneResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 ID의 사주 해석 결과 없음",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<FortuneResponseDto> getFortuneRequest(
            @Parameter(description = "사주 해석 요청 ID", required = true) @PathVariable Long id) {
        FortuneResponseDto responseDto = fortuneService.getFortuneRequestById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @Operation(
            summary = "모든 사주 해석 결과 조회",
            description = "시스템에 저장된 모든 사주 해석 결과를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FortuneResponseDto.class))
            )
    })
    public ResponseEntity<List<FortuneResponseDto>> getAllFortuneRequests() {
        List<FortuneResponseDto> responseDtos = fortuneService.getAllFortuneRequests();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/search")
    @Operation(
            summary = "이름으로 사주 해석 결과 검색",
            description = "특정 이름으로 등록된 모든 사주 해석 결과를 검색합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 성공",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FortuneResponseDto.class))
            )
    })
    public ResponseEntity<List<FortuneResponseDto>> getFortuneRequestsByName(
            @Parameter(description = "검색할 이름", required = true) @RequestParam String name) {
        List<FortuneResponseDto> responseDtos = fortuneService.getFortuneRequestsByName(name);
        return ResponseEntity.ok(responseDtos);
    }
}