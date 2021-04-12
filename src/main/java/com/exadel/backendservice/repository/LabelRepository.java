package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Tech, Integer> {
}
