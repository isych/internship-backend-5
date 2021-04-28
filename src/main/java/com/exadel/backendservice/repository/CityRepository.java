package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByName(String name);

    Set<City> findAllByNameIn(List<String> name); //todo переписать , работает не так как надо

    boolean existsByName(String name);

    boolean existsAllByNameIn(List<String> cities); //todo переписать , работает не так как надо
}