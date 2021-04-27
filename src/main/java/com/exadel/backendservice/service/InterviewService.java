package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;

public interface InterviewService {
    InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto);

    InterviewRespDto updateInterviewer(Integer interviewId, Integer employeeId);
}
