package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TechRepository extends JpaRepository<Tech, Integer> {
    Optional<Tech> findByName(String name);

    Set<Tech> findAllByNameIn(List<String> name);

    boolean existsByName(String name);

    boolean existsAllByNameIn(List<String> name);
}
