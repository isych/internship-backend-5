package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
