package com.exadel.backendservice.mapper.employee;

import com.exadel.backendservice.dto.resp.RecommendedEmployeeDto;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.EmployeeTimeslot;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.mapper.timeslot.TimeslotsPreferenceMapper;
import com.exadel.backendservice.mapper.interview.EmployeeInterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class RecommendedEmployeeMapper extends AbstractMapper<Employee, RecommendedEmployeeDto> {
    private final TimeslotsPreferenceMapper timeslotsPreferenceMapper;
    private final EmployeeInterviewMapper interviewMapper;

    @Autowired
    public RecommendedEmployeeMapper(TimeslotsPreferenceMapper timeslotsPreferenceMapper, EmployeeInterviewMapper interviewMapper) {
        super(Employee.class, RecommendedEmployeeDto.class);
        this.timeslotsPreferenceMapper = timeslotsPreferenceMapper;
        this.interviewMapper = interviewMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Employee.class, RecommendedEmployeeDto.class)
                .addMappings(m -> m.skip(RecommendedEmployeeDto::setEmployeeInterviews))
                .addMappings(m -> m.skip(RecommendedEmployeeDto::setTimeslotPreferences))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Employee source, RecommendedEmployeeDto destination) {
        Set<EmployeeTimeslot> timeslots = source.getTimeslots();
        Set<Interview> interviews = source.getInterviews();
        destination.setEmployeeInterviews(interviewMapper.toDto(interviews));
        destination.setTimeslotPreferences(timeslotsPreferenceMapper.toDto(timeslots));
    }
}
