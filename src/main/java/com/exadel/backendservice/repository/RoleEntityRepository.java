package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
    List<RoleEntity> findAll();
}
