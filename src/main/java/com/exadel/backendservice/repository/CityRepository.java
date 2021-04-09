package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
