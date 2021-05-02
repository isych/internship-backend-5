package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.EmployeeTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeTimeslotRepository extends JpaRepository<EmployeeTimeslot, UUID>{
    List<EmployeeTimeslot> findAll();
}
