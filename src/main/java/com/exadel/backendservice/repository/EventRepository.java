package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    @Override
    List<EventEntity> findAll();
}
