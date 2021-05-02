package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.EmployeeTimeslotDto;
import com.exadel.backendservice.entity.EmployeeTimeslot;
import com.exadel.backendservice.mapper.timeslot.TimeslotMapper;
import com.exadel.backendservice.repository.EmployeeTimeslotRepository;
import com.exadel.backendservice.service.EmployeeTimeslotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeTimeslotServiceImpl implements EmployeeTimeslotService {

   private final EmployeeTimeslotRepository employeeTimeslotRepository;
   private final TimeslotMapper timeslotMapper;

    @Override
    public boolean deleteById(UUID id) {
        if (!employeeTimeslotRepository.existsById(id)) {
            return false;
        }
        employeeTimeslotRepository.deleteById(id);
        return true;
    }

    @Override
    public List<EmployeeTimeslotDto> getListByEmployeeId(UUID employee) {
        List<EmployeeTimeslot> employeeTimeslots = employeeTimeslotRepository.findAll().stream().filter(entity -> entity.getEmployee().id.equals(employee)).collect(Collectors.toList());
        List<EmployeeTimeslotDto> employeeTimeslotDtos = employeeTimeslots.stream().map(entity->timeslotMapper.toDto(entity)).collect(Collectors.toList());
        return employeeTimeslotDtos;
    }
}
