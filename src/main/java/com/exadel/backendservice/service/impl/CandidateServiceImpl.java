package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.converter.RegisterCandidateMapper;
import com.exadel.backendservice.mapper.converter.SearchCandidateMapper;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.model.PreferredTime;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final RegisterCandidateMapper registerCandidateMapper;
    private final SearchCandidateMapper searchCandidateMapper;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository,
                                RegisterCandidateMapper registerCandidateMapper,
                                SearchCandidateMapper searchCandidateMapper) {
        this.candidateRepository = candidateRepository;
        this.registerCandidateMapper = registerCandidateMapper;
        this.searchCandidateMapper = searchCandidateMapper;
    }

    @Override
    public Candidate registerCandidate(RegisterCandidateDto candidateDto) {
        log.debug("CandidateDto ready to convert: {}", candidateDto);
        Candidate entity = registerCandidateMapper.toEntity(candidateDto);
        Candidate candidateWithId = candidateRepository.save(entity);
        log.debug("Candidate with ID: {}", entity);
        return candidateWithId;
    }

    @Override
    public Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable) {
        Page<Candidate> page = candidateRepository.findAll(pageable);
        List<SearchCandidateDto> candidateDtoList = page.get().map(searchCandidateMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(candidateDtoList);
    }

    @Override
    public List<String> getAllStatuses() {
        List<String> statusList = Stream.of(CandidateStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return statusList;
    }

    @Override
    public List<String> getInterviewStatuses() {
        List<String> interviewStatusesList = Stream.of(InterviewProcess.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return interviewStatusesList;
    }

    @Override
    public List<String> getAllPreferredTime() {
        List<String> preferredTimesList = Stream.of(PreferredTime.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return preferredTimesList;
    }
}
