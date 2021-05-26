package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.EmployeeDto;
import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.EmployeeTimeslot;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.mapper.role.InterviewersByRoleMapper;
import com.exadel.backendservice.mapper.role.RoleResponseMapper;
import com.exadel.backendservice.repository.EmployeeRepository;
import com.exadel.backendservice.repository.EmployeeTimeslotRepository;
import com.exadel.backendservice.repository.InterviewRepository;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    private final EmployeeTimeslotRepository employeeTimeslotRepository;
    private final InterviewRepository interviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
        List<Role> roles = roleRepository.findAll();
        return interviewersByRoleMapper.toDto(roles);
    }

    @Override
    public Boolean deleteEmployee(UUID id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Optional<List<EmployeeTimeslot>> optionalEmployeeTimeslots = employeeTimeslotRepository.findAllByEmployee_Id(optionalEmployee.get().getId());
            Optional<List<Interview>> optionalInterviews = interviewRepository.findAllByEmployee_Id(optionalEmployee.get().getId());
            optionalEmployeeTimeslots.ifPresent(employeeTimeslots -> employeeTimeslots.forEach(employeeTimeslotRepository::delete));
            optionalInterviews.ifPresent(interviews -> interviews.forEach(interviewRepository::delete));
            employeeRepository.delete(optionalEmployee.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        if (employeeDto != null) {
            Optional<Role> optionalRole = roleRepository.findByName("ROLE_" + employeeDto.getRole().toUpperCase());
            if (optionalRole.isPresent() && emailIsUnique(employeeDto.getEmail())) {
                Employee employee = new Employee();
                employee.setFullName(employeeDto.getFullName());
                employee.setEmail(employeeDto.getEmail());
                employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
                employee.setRole(optionalRole.get());
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    @Override
    public List<LocalDateTime> getAllInterviewsById(UUID id) {
        Optional<List<Interview>> interviewsOptional = interviewRepository.findAllByEmployee_Id(id);
        List<LocalDateTime> dateList = new ArrayList<>();
        if (interviewsOptional.isPresent()) {
            List<Interview> interviews = interviewsOptional.get();
            for (Interview interview : interviews) {
                dateList.add(interview.getStartTime());
            }
            return dateList;
        }
        throw new DBNotFoundException("Unable to find interviews by employee id");
    }

    private Boolean emailIsUnique(String email) {
        return employeeRepository.findByEmail(email) == null;
    }
}
