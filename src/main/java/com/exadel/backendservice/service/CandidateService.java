package com.exadel.backendservice.service;


import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CandidateService {
    CandidateRespDto registerCandidate(RegisterCandidateDto candidateDto);

    Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable);

    DetailedCandidateDto getDetailedCandidateDto(UUID id);

    List<String> getAllStatuses();

    List<String> getInterviewStatuses();

    List<String> getAllPreferredTime();

    CandidateRespDto uploadCv(UUID id, MultipartFile file);

    byte[] downloadCv(UUID id) throws IOException;

    Boolean hasCv(UUID id);

    String getCvName(UUID id);

    CandidateRespDto updateStatus(UUID id, CandidateStatus status);

    CandidateRespDto updateInterviewStatus(UUID id, InterviewProcess awaitingHr, HttpServletRequest request);

    List<SearchCandidateDto> getCandidatesWithFilter(List<String> primaryTech, List<String> interviewProccess, List<String> status, List<String> country, List<String> event);

    Set<String> getCountries();

    Set<String> getCandidatesTech();

    Set<String> getCandidatesEvents();
}