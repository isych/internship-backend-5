package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    @Override
    public InterviewRespDto saveFeedback(Integer id, String feedback) {
            Interview interview;
            Optional<Interview> interviewOptional = interviewRepository.findById(id);
            if (interviewOptional.isPresent()) {
                interview = interviewOptional.get();
                interview.setFeedback(feedback);
                interviewRepository.save(interview);
                return interviewMapper.toDto(interview);
            }
            throw new DBNotFoundException("Interview with this id doesn't found");
    }
}

