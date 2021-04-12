package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SearchCandidateMapper extends AbstractMapper<Candidate, SearchCandidateDto> {

    public SearchCandidateMapper() {
        super(Candidate.class, SearchCandidateDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Candidate.class, SearchCandidateDto.class)
                .addMappings(m -> m.skip(SearchCandidateDto::setEventName))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Candidate source, SearchCandidateDto destination) {
        String name = source.getEvent().getName();
        destination.setEventName(name);
    }
}
