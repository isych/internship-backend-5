package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.CityEntity;
import com.exadel.backendservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
