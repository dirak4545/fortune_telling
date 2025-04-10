package com.example.fortunetelling.service;

import com.example.fortunetelling.dto.FortuneRequestDto;
import com.example.fortunetelling.dto.FortuneResponseDto;
import com.example.fortunetelling.entity.FortuneRequest;
import com.example.fortunetelling.exception.ResourceNotFoundException;
import com.example.fortunetelling.repository.FortuneRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FortuneService {
    private final FortuneRequestRepository fortuneRequestRepository;
    private final ChatGptService chatGptService;

    @Transactional
    public FortuneResponseDto createFortuneRequest(FortuneRequestDto requestDto) {
        String fortuneResult = chatGptService.getFortuneTelling(requestDto);

        FortuneRequest fortuneRequest = FortuneRequest.builder()
                .name(requestDto.getName())
                .birthDateTime(requestDto.getBirthDateTime())
                .gender(requestDto.getGender())
                .question(requestDto.getQuestion())
                .fortuneResult(fortuneResult)
                .createdAt(LocalDateTime.now())
                .build();

        FortuneRequest savedRequest = fortuneRequestRepository.save(fortuneRequest);
        return convertToResponseDto(savedRequest);
    }

    @Transactional(readOnly = true)
    public FortuneResponseDto getFortuneRequestById(Long id) {
        FortuneRequest fortuneRequest = fortuneRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 ID의 사주 조회 요청을 찾을 수 없습니다: " + id));

        return convertToResponseDto(fortuneRequest);
    }

    @Transactional(readOnly = true)
    public List<FortuneResponseDto> getAllFortuneRequests() {
        List<FortuneRequest> requests = fortuneRequestRepository.findAll();
        return requests.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FortuneResponseDto> getFortuneRequestsByName(String name) {
        List<FortuneRequest> requests = fortuneRequestRepository.findByName(name);
        return requests.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private FortuneResponseDto convertToResponseDto(FortuneRequest fortuneRequest) {
        return FortuneResponseDto.builder()
                .id(fortuneRequest.getId())
                .name(fortuneRequest.getName())
                .birthDateTime(fortuneRequest.getBirthDateTime())
                .gender(fortuneRequest.getGender())
                .question(fortuneRequest.getQuestion())
                .fortuneResult(fortuneRequest.getFortuneResult())
                .createdAt(fortuneRequest.getCreatedAt())
                .build();
    }
}