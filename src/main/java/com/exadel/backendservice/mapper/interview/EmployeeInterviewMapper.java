package com.exadel.backendservice.mapper.interview;

import com.exadel.backendservice.dto.resp.EmployeeInterviewDto;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInterviewMapper extends AbstractMapper<Interview, EmployeeInterviewDto> {
    public EmployeeInterviewMapper() {
        super(Interview.class, EmployeeInterviewDto.class);
    }
}
