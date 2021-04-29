package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.model.ObjectForFeedbackPage;

public interface InterviewService {
    InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto);

    InterviewRespDto updateInterviewer(Integer interviewId, Integer employeeId);

    InterviewRespDto saveFeedback(String hash, String feedback);

    boolean deleteById(Integer id);

    ObjectForFeedbackPage getObjectForFeedbackPage(String hash);
}

