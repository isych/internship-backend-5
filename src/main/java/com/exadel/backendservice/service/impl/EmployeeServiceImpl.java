package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.entity.Employee;
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

    private final EmployeeRepository employeeRepository;
    private final RoleEntityRepository roleEntityRepository;
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
        return roleEntityRepository.findAll().stream()
                .map(elem -> elem.getName().substring(5))
                .collect(Collectors.toList());
    }
}
