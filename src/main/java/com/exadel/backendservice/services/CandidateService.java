package com.exadel.backendservice.services;


import com.exadel.backendservice.dto.CandidateDto;

public interface CandidateService {

    boolean create(CandidateDto candidateDto);
}
