package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
    List<Role> findAll();
    List<Role> findAllByNameIsNot(String  name);
}
