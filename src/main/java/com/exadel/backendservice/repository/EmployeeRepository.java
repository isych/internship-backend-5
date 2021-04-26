package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    List<Employee> findAllByRoleEntity_Name(String roleName);
}
