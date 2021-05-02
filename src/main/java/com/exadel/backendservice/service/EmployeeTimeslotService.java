package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.EmployeeTimeslotDto;
import java.util.List;
import java.util.UUID;

public interface EmployeeTimeslotService {
    boolean deleteById(UUID id);
    List<EmployeeTimeslotDto> getListByEmployeeId(UUID id);
}
