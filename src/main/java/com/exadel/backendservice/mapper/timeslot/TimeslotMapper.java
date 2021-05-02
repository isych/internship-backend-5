package com.exadel.backendservice.mapper.timeslot;

import com.exadel.backendservice.dto.resp.EmployeeTimeslotDto;
import com.exadel.backendservice.entity.EmployeeTimeslot;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeslotMapper extends AbstractMapper<EmployeeTimeslot, EmployeeTimeslotDto> {
    public TimeslotMapper() {
        super(EmployeeTimeslot.class, EmployeeTimeslotDto.class);
    }
}
