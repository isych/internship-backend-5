package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DetailedCandidateMapper extends AbstractMapper<Candidate, DetailedCandidateDto> {
    public DetailedCandidateMapper() {
        super(Candidate.class, DetailedCandidateDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Candidate.class, DetailedCandidateDto.class)
                .addMappings(m -> m.skip(DetailedCandidateDto::setCity))
                .addMappings(m -> m.skip(DetailedCandidateDto::setCountry))
                .addMappings(m -> m.skip(DetailedCandidateDto::setEventName))
                .addMappings(m -> m.skip(DetailedCandidateDto::setInterviews))
                .addMappings(m -> m.skip(DetailedCandidateDto::setPrimaryTech))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Candidate source, DetailedCandidateDto destination) {
        String city = source.getCity().getName();
        String country = source.getCity().getCountry().getName();
        String eventName = source.getEvent().getName();
        String primaryTech = source.getPrimaryTech().getName();
        //TODO InterviewDto
        destination.setCity(city);
        destination.setCountry(country);
        destination.setEventName(eventName);
        destination.setPrimaryTech(primaryTech);
    }
}
