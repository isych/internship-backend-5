package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.mapper.converter.CreateInterviewMapper;
import com.exadel.backendservice.mapper.converter.InterviewRespMapper;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        if(optionalInterview.isEmpty()) {
            throw new DBNotFoundException("Interview with this id haven't found");
        } else {
            interview = optionalInterview.get();
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee;
        if(optionalEmployee.isEmpty()) {
            throw new DBNotFoundException("Employee with this id haven't found");
        } else {
            employee = optionalEmployee.get();
            interview.setEmployee(employee);
        }
        return interviewRespMapper.toDto(interviewRepository.save(interview));
    }

     @Override
        public InterviewRespDto saveFeedback(UUID id, String feedback) {
            Interview interview;
            Optional<Interview> interviewOptional = interviewRepository.findById(id);
            if (interviewOptional.isPresent()) {
                interview = interviewOptional.get();
                interview.setFeedback(feedback);
                interviewRepository.save(interview);
                return interviewRespMapper.toDto(interview);
            }
            throw new DBNotFoundException("Interview with this id doesn't found");
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
