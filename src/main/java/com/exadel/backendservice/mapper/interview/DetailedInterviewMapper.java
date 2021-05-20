package com.exadel.backendservice.mapper.interview;

import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.DetailedInterviewDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.mapper.candidate.DetailedCandidateMapper;
import com.exadel.backendservice.mapper.event.DetailedEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DetailedInterviewMapper extends AbstractMapper<Interview, DetailedInterviewDto> {

    private final DetailedCandidateMapper candidateMapper;
    private final DetailedEventMapper eventMapper;

    @Autowired
    public DetailedInterviewMapper(DetailedCandidateMapper candidateMapper, DetailedEventMapper eventMapper) {
        super(Interview.class, DetailedInterviewDto.class);
        this.candidateMapper = candidateMapper;
        this.eventMapper = eventMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Interview.class, DetailedInterviewDto.class)
                .addMappings(m -> m.skip(DetailedInterviewDto::setEvent))
                .addMappings(m -> m.skip(DetailedInterviewDto::setCandidate))
                .addMappings(m -> m.skip(DetailedInterviewDto::setInterviewer))
                .addMappings(m -> m.skip(DetailedInterviewDto::setInterviewDate))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Interview source, DetailedInterviewDto destination) {
        Candidate candidate = source.getCandidate();
        Event event = candidate.getEvent();

        DetailedCandidateDto candidateDto = candidateMapper.toDto(candidate);
        DetailedEventDto eventDto = eventMapper.toDto(event);

        destination.setEvent(eventDto);
        destination.setCandidate(candidateDto);
        destination.setInterviewer(source.getEmployee().getFullName());
        destination.setInterviewDate(source.getStartTime());
    }
}
