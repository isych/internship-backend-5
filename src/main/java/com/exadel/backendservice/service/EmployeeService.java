package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Employee;

import java.util.List;


public interface EmployeeService {

    List<RoleRespDto> getInterviewersRoles();

    List<InterviewersByRoleDto> getInterviewersForCandidate();

    Employee findByEmail(String email);

    Employee findByEmailAndPassword(String email, String password);

    List<String> getListRoles();

}
