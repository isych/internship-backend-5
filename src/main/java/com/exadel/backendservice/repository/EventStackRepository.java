package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.EventStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStackRepository extends JpaRepository<EventStackEntity, Integer> {
}
