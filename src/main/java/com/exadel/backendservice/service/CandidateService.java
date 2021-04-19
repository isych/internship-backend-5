package com.exadel.backendservice.service;


import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CandidateService {
    CandidateWithIdDto registerCandidate(RegisterCandidateDto candidateDto);

    Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable);

    DetailedCandidateDto getDetailedCandidateDto(Integer id);

    List<String> getAllStatuses();

    List<String> getInterviewStatuses();

    List<String> getAllPreferredTime();

    Optional<CandidateWithIdDto> uploadCv(Integer id, MultipartFile file);

    byte[] downloadCv(Integer id) throws IOException;

}