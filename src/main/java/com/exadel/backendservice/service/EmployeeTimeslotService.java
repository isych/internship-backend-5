package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.EmployeeDto;
import com.exadel.backendservice.dto.resp.EmployeeTimeslotDto;
import com.exadel.backendservice.dto.resp.TimeslotPreferenceDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeTimeslotService {
    boolean deleteById(UUID id);

    List<EmployeeTimeslotDto> getListByEmployeeId(UUID id);

    EmployeeTimeslotDto addToEmployee(TimeslotPreferenceDto timeslotPreferenceDto, UUID id);
}
