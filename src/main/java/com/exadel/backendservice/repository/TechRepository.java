package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechRepository extends JpaRepository<Tech, Integer> {
    Optional<Tech> findByName(String name);
}
