package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByRole(Role id);
}
