package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.converter.CandidateMapper;
import com.exadel.backendservice.mapper.converter.DetailedCandidateMapper;
import com.exadel.backendservice.mapper.converter.RegisterCandidateMapper;
import com.exadel.backendservice.mapper.converter.SearchCandidateMapper;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.model.PreferredTime;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final RegisterCandidateMapper registerCandidateMapper;
    private final SearchCandidateMapper searchCandidateMapper;
    private final DetailedCandidateMapper detailedCandidateMapper;
    private final CandidateMapper candidateMapper;

    @Override
    public CandidateWithIdDto registerCandidate(RegisterCandidateDto registerCandidateDto) {
        log.debug("CandidateDto ready to convert: {}", registerCandidateDto);
        Candidate candidate = registerCandidateMapper.toEntity(registerCandidateDto);
        Candidate savedCandidate = candidateRepository.save(candidate);
        CandidateWithIdDto candidateWithIdDto = candidateMapper.toDto(savedCandidate);
        log.debug("Candidate with ID: {}", candidateWithIdDto.getId());
        return candidateWithIdDto;
    }

    @Override
    public Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable) {
        Page<Candidate> page = candidateRepository.findAll(pageable);
        List<SearchCandidateDto> candidateDtoList = page.get().map(searchCandidateMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(candidateDtoList);
    }

    @Override
    public DetailedCandidateDto getDetailedCandidateDto(Integer id) {
        Candidate candidate = candidateRepository.getOne(id);
        DetailedCandidateDto detailedCandidateDto = detailedCandidateMapper.toDto(candidate);
        return detailedCandidateDto;
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
