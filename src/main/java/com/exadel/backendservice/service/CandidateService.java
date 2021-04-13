package com.exadel.backendservice.service;


import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateService {
    CandidateWithIdDto registerCandidate(RegisterCandidateDto candidateDto);

    Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable);

    DetailedCandidateDto getDetailedCandidateDto(Integer id);
}
