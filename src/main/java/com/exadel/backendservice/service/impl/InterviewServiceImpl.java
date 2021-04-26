package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.InterviewDto;
import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.converter.CreateInterviewMapper;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final CreateInterviewMapper createInterviewMapper;

    @Override
    public CreateInterviewDto saveInterview(CreateInterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.toEntity(createInterviewDto);
        System.out.println(interview);
        log.debug("Create interview -> {}", interview);
        CreateInterviewDto createdInterview = createInterviewMapper.toDto(interviewRepository.save(interview));
        System.out.println(createdInterview);
        log.debug("Created interview -> {}", createdInterview);
        return createdInterview;
    }
}
