package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.DynamicInterviewLink;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.mapper.interview.CreateInterviewMapper;
import com.exadel.backendservice.mapper.interview.InterviewRespMapper;
import com.exadel.backendservice.model.MessageBody;
import com.exadel.backendservice.model.MessageBodyBase;
import com.exadel.backendservice.model.MessageSubject;
import com.exadel.backendservice.model.ObjectForFeedbackPage;
import com.exadel.backendservice.repository.DynamicInterviewLinkRepository;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.service.InterviewService;
import com.exadel.backendservice.service.utils.FeedbackLinkGenerator;
import com.exadel.backendservice.service.utils.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    private final MailSender mailSender;

    @Override
    public InterviewRespDto saveInterview(CreateInterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.toEntity(createInterviewDto);
        return getInterviewRespDto(createInterviewDto, interview);
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
                    DateTimeFormatter.ISO_LOCAL_TIME.format(interview.getEndTime()),
                    interview.getEmployee().getFullName()
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

    @Override
    public List<InterviewRespDto> getInterviewsForEmployee(UUID idEmployee) {
        return interviewRepository.findAllByEmployee_Id(idEmployee)
                .map(interviews -> interviews.stream().map(interviewRespMapper::toDto).collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public InterviewRespDto editInterview(UUID id, CreateInterviewDto createInterviewDto) {
        Interview interview = createInterviewMapper.toEntity(createInterviewDto);
        interview.setId(id);
        return getInterviewRespDto(createInterviewDto, interview);
    }

    private InterviewRespDto getInterviewRespDto(CreateInterviewDto createInterviewDto, Interview interview) {
        interview.setEndTime(createInterviewDto.getStartTime().plusHours(1));
        InterviewRespDto createdInterview = interviewRespMapper.toDto(interviewRepository.save(interview));

        List<MessageBodyBase> messageBodyBases = new ArrayList<>();
        if(interview.getEmployee().getRole().getName().equals("ROLE_TECH")){
            messageBodyBases.add(MessageBodyBase.APPOINTED_TECH_INTERVIEW);
        }else  if(interview.getEmployee().getRole().getName().equals("ROLE_ADMIN")){
            messageBodyBases.add(MessageBodyBase.APPOINTED_HR_INTERVIEW);
        }
        try {
            mailSender.send(
                    interview.getCandidate().getEmail(),
                    MessageSubject.INTERVIEW.getSubject(),
                    new MessageBody(
                            interview.getCandidate().getFullName(),
                            messageBodyBases,
                            interview.getStartTime()).getBody()
            );
            mailSender.send(
                    interview.getEmployee().getEmail(),
                    MessageSubject.INTERVIEW.getSubject(),
                    new MessageBody(
                            interview.getCandidate().getFullName(),
                            messageBodyBases,
                            interview.getStartTime()).getBody()
            );
        }catch (MailException ex) {
            throw new ApiResponseException("Internal error: mail can't be send to candidate");
        }
        return createdInterview;
    }
}
