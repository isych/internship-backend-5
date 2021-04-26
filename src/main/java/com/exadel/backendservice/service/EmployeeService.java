package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;

import java.util.List;


public interface EmployeeService {

    List<RoleRespDto> getInterviewersRoles();

    List<InterviewersByRoleDto> getInterviewersForCandidate();
}
