package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAll();
}
