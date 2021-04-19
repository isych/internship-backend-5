package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

    Optional<Event> findByName(String name);

    Boolean existsByName(String name);
}
