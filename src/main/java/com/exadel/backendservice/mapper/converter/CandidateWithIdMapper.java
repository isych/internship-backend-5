package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CandidateWithIdMapper extends AbstractMapper<Candidate, CandidateWithIdDto> {
    public CandidateWithIdMapper() {
        super(Candidate.class, CandidateWithIdDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Candidate.class, CandidateWithIdDto.class)
                .addMappings(m -> m.skip(CandidateWithIdDto::setCity))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Candidate source, CandidateWithIdDto destination) {
        String city = source.getCity().getName();
        //TODO InterviewDto
        destination.setCity(city);
    }
}
