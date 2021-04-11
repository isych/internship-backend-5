package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.mapper.AbstractMapper;

import javax.annotation.PostConstruct;

public class DetailedCandidateMapper extends AbstractMapper<Candidate, DetailedCandidateDto> {
    public DetailedCandidateMapper() {
        super(Candidate.class, DetailedCandidateDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Candidate.class, DetailedCandidateDto.class)
                .addMappings(m -> m.skip(DetailedCandidateDto::setCity))
                .addMappings(m -> m.skip(DetailedCandidateDto::setCountry))
                .addMappings(m -> m.skip(DetailedCandidateDto::setEventStackId))
                .addMappings(m -> m.skip(DetailedCandidateDto::setEventStackName))
                .addMappings(m -> m.skip(DetailedCandidateDto::setEventName))
                .addMappings(m -> m.skip(DetailedCandidateDto::setInterviews))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Candidate source, DetailedCandidateDto destination) {
        String city = source.getCity().getName();
        String country = source.getCity().getCountry().getName();
        Integer eventStackId = source.getEventStack().getId();
        String eventStackName = source.getEventStack().getName();
        String eventName = source.getEventStack().getEvent().getName();
        //TODO InterviewDto
        destination.setCity(city);
        destination.setCountry(country);
        destination.setEventStackId(eventStackId);
        destination.setEventStackName(eventStackName);
        destination.setEventName(eventName);
    }
}
