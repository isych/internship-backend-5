package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;

import java.util.UUID;

public interface InterviewService {
    InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto);

    InterviewRespDto updateInterviewer(UUID interviewId, UUID employeeId);

    InterviewRespDto saveFeedback(UUID id, String feedback);

    boolean deleteById(UUID id);

}

