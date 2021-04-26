package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateInterviewDto;

public interface InterviewService {
    CreateInterviewDto saveInterview(CreateInterviewDto createInterviewDto);
}
