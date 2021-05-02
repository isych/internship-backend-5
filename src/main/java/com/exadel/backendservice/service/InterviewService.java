package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.model.ObjectForFeedbackPage;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
    InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto);

    InterviewRespDto updateInterviewer(UUID interviewId, UUID employeeId);

    InterviewRespDto saveFeedback(String hash, String feedback);

    boolean deleteById(UUID id);

    ObjectForFeedbackPage getObjectForFeedbackPage(String hash);

    List<InterviewRespDto> getInterviewsForEmployee(UUID idEmployee);
}

