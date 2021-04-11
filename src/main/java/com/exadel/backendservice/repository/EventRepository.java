package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    List<Event> findAll();
}
