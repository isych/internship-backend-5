package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.repository.EmployeeTimeslotRepository;
import com.exadel.backendservice.service.EmployeeTimeslotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeTimeslotServiceImpl implements EmployeeTimeslotService {

   private final EmployeeTimeslotRepository employeeTimeslotRepository;

    @Override
    public boolean deleteById(Integer id) {
        if (!employeeTimeslotRepository.existsById(id)) {
            return false;
        }
        employeeTimeslotRepository.deleteById(id);
        return true;
    }
}
