package com.exadel.backendservice.mapper.timeslot;

import com.exadel.backendservice.dto.resp.TimeslotPreferenceDto;
import com.exadel.backendservice.entity.EmployeeTimeslot;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeslotsPreferenceMapper extends AbstractMapper<EmployeeTimeslot, TimeslotPreferenceDto> {

    public TimeslotsPreferenceMapper() {
        super(EmployeeTimeslot.class, TimeslotPreferenceDto.class);
    }
}
