package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.converter.RegisterCandidateMapper;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final RegisterCandidateMapper registerCandidateMapper;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, RegisterCandidateMapper registerCandidateMapper) {
        this.candidateRepository = candidateRepository;
        this.registerCandidateMapper = registerCandidateMapper;
    }

    @Override
    public Candidate registerCandidate(RegisterCandidateDto candidateDto) {
        log.debug("CandidateDto ready to convert: {}", candidateDto);
        Candidate entity = registerCandidateMapper.toEntity(candidateDto);
        Candidate candidateWithId = candidateRepository.save(entity);
        log.debug("Candidate with ID: {}", entity);
        return candidateWithId;
    }
}
