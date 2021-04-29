package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.DynamicInterviewLink;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.mapper.converter.CreateInterviewMapper;
import com.exadel.backendservice.mapper.converter.InterviewRespMapper;
import com.exadel.backendservice.model.ObjectForFeedbackPage;
import com.exadel.backendservice.repository.DynamicInterviewLinkRepository;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.service.InterviewService;
import com.exadel.backendservice.service.utils.FeedbackLinkGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final EmployeeRepository employeeRepository;

    private final CreateInterviewMapper createInterviewMapper;
    private final InterviewRespMapper interviewRespMapper;
    private final DynamicInterviewLinkRepository dynamicInterviewLinkRepository;
    private final FeedbackLinkGenerator feedbackLinkGenerator;

    @Override
    public InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.toEntity(createInterviewDto);
        log.debug("Create interview -> {}", interview);
        InterviewRespDto createdInterview = interviewRespMapper.toDto(interviewRepository.save(interview));
        log.debug("Created interview -> {}", createdInterview);
        return createdInterview;
    }

    @Override
    public InterviewRespDto updateInterviewer(UUID interviewId, UUID employeeId) {
        Optional<Interview> optionalInterview = interviewRepository.findById(interviewId);
        Interview interview;
        if (optionalInterview.isEmpty()) {
            throw new DBNotFoundException("Interview with this id haven't found");
        } else {
            interview = optionalInterview.get();
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee;
        if (optionalEmployee.isEmpty()) {
            throw new DBNotFoundException("Employee with this id haven't found");
        } else {
            employee = optionalEmployee.get();
            interview.setEmployee(employee);
        }
        return interviewRespMapper.toDto(interviewRepository.save(interview));
    }

    @Override
    public InterviewRespDto saveFeedback(String hash, String feedback) {
        DynamicInterviewLink dynamicInterviewLink = dynamicInterviewLinkRepository.findByCode(hash);
        if (dynamicInterviewLink != null) {
            Interview interview = interviewRepository.findById(dynamicInterviewLink.getInterviewId()).get();
            interview.setFeedback(feedback);
            interviewRepository.save(interview);
            feedbackLinkGenerator.removeHashFromDb(hash);
            return interviewRespMapper.toDto(interview);

        } else {
            return null;
        }
    }

    @Override
    public ObjectForFeedbackPage getObjectForFeedbackPage(String hash) {
        DynamicInterviewLink dynamicInterviewLink = dynamicInterviewLinkRepository.findByCode(hash);
        if (dynamicInterviewLink == null) {
            return null;
        } else {
            Interview interview = interviewRepository.findById(dynamicInterviewLink.getInterviewId()).get();
            Candidate candidate = interview.getCandidate();
            return new ObjectForFeedbackPage(
                    candidate.getFullName(),
                    candidate.getEmail(),
                    candidate.getPhone(),
                    candidate.getCity().getCountry().getName(),
                    candidate.getCity().getName(),
                    candidate.getPrimaryTech().getName(),
                    DateTimeFormatter.ISO_LOCAL_DATE.format(interview.getEndTime()),
                    DateTimeFormatter.ISO_LOCAL_TIME.format(interview.getEndTime())
            );
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!interviewRepository.existsById(id)) {
            return false;
        }
        interviewRepository.deleteById(id);
        return true;
    }
}
