package com.exadel.backendservice.service;


import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {
    Candidate registerCandidate(RegisterCandidateDto candidateDto);

    Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable);

    List<String> getAllStatuses();

    List<String> getInterviewStatuses();

    List<String> getAllPreferredTime();
}
