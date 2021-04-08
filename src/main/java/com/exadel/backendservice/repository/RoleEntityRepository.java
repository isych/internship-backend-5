package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleEntityRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    List<Role> findAll();
}
