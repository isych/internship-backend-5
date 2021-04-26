package com.exadel.backendservice.mapper.interview;

import com.exadel.backendservice.dto.DetailedCandidateInterviewDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DetailedCandidateInterviewMapper extends AbstractMapper<Interview, DetailedCandidateInterviewDto> {
    public DetailedCandidateInterviewMapper() {
        super(Interview.class, DetailedCandidateInterviewDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Interview.class, DetailedCandidateInterviewDto.class)
                .addMappings(m -> m.skip(DetailedCandidateInterviewDto::setInterviewerName))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Interview source, DetailedCandidateInterviewDto destination) {
        destination.setInterviewerName(source.getEmployee().getFullName());
    }
}
