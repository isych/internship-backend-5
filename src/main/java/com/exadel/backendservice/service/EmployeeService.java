package com.exadel.backendservice.service;

import com.exadel.backendservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findByEmail(String email);
    Employee findByEmailAndPassword(String email, String password);
    List<String> getListRoles();
}
