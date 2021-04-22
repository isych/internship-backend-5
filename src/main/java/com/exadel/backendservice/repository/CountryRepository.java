package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Override
    List<Country> findAll();

    boolean existsByName(String name);

}
