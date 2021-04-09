package com.exadel.backendservice.service;


import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.entity.Candidate;

public interface CandidateService {
    Candidate registerCandidate(RegisterCandidateDto candidateDto);
}
