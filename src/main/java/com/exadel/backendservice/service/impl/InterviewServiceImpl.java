package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.converter.CreateInterviewMapper;
import com.exadel.backendservice.mapper.converter.InterviewRespMapper;
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
    private final InterviewRespMapper interviewRespMapper;

    @Override
    public InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.toEntity(createInterviewDto);
        log.debug("Create interview -> {}", interview);
        InterviewRespDto createdInterview = interviewRespMapper.toDto(interviewRepository.save(interview));
        log.debug("Created interview -> {}", createdInterview);
        return createdInterview;
    }
}
