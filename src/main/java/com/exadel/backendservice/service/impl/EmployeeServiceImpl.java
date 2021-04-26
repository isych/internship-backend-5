package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.mapper.role.InterviewersByRoleMapper;
import com.exadel.backendservice.mapper.employee.RecommendedEmployeeMapper;
import com.exadel.backendservice.mapper.role.RoleResponseMapper;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final String SUPERADMIN_ROLE_NAME = "ROLE_SUPERADMIN";

    private final RoleResponseMapper roleMapper;
    private final RecommendedEmployeeMapper recommendedEmployeeMapper;
    private final InterviewersByRoleMapper interviewersByRoleMapper;

    private final RoleEntityRepository roleRepository;
    private final EmployeeRepository employeeRepository;
    private final CandidateRepository candidateRepository;


    @Override
    public List<RoleRespDto> getInterviewersRoles() {
        List<Role> roles = roleRepository.findAllByNameIsNot(SUPERADMIN_ROLE_NAME);
        return roleMapper.toDto(roles);
    }

    @Override
    public List<InterviewersByRoleDto> getInterviewersForCandidate() {
        List<Role> roles = roleRepository.findAllByNameIsNot(SUPERADMIN_ROLE_NAME);
        return interviewersByRoleMapper.toDto(roles);

    }


}
