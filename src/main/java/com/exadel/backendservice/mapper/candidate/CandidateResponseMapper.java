package com.exadel.backendservice.mapper.candidate;

import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateResponseMapper extends AbstractMapper<Candidate, CandidateRespDto> {
    public CandidateResponseMapper() {
        super(Candidate.class, CandidateRespDto.class);
    }

}
