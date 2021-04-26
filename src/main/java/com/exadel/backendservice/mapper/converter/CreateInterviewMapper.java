package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateInterviewMapper extends AbstractMapper<Interview, CreateInterviewDto> {

    @Autowired
    public CreateInterviewMapper() {
        super(Interview.class, CreateInterviewDto.class);
    }
}
