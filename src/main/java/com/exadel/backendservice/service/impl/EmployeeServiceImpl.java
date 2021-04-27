package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.mapper.role.InterviewersByRoleMapper;
import com.exadel.backendservice.mapper.role.RoleResponseMapper;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final String SUPERADMIN_ROLE_NAME = "ROLE_SUPERADMIN";

    private final RoleResponseMapper roleMapper;
    private final InterviewersByRoleMapper interviewersByRoleMapper;

    private final RoleEntityRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) {
        Employee employee = findByEmail(email);
        if (employee != null) {
            if (passwordEncoder.matches(password, employee.getPassword())) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<String> getListRoles() {
        return roleRepository.findAll().stream()
                .map(elem -> elem.getName().substring(5))
                .collect(Collectors.toList());
    }

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
