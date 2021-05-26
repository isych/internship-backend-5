package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.EmployeeDto;
import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface EmployeeService {

    List<RoleRespDto> getInterviewersRoles();

    List<InterviewersByRoleDto> getInterviewersForCandidate();

    Employee findByEmail(String email);

    Employee findByEmailAndPassword(String email, String password);

    List<String> getListRoles();

    Boolean deleteEmployee(UUID id);

    Employee saveEmployee(EmployeeDto employeeDto);

    List<LocalDateTime> getAllInterviewsById(UUID id);
}
