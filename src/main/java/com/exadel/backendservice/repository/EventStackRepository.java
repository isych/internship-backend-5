package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.EventStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStackRepository extends JpaRepository<EventStack, Integer> {
}
