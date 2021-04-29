package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Employee;

import com.exadel.backendservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByRole(Role id);
    Employee findByEmail(String email);
//    List<Employee> findAllByRoleEntity_Name(String roleName);

}
