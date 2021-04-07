package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    List<EventEntity> findAll();
}
