package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID> {
    List<Interview> findAllByCandidate_Id(UUID id);
    Optional<Interview> findById(UUID id);
    Optional<List<Interview>> findAllByEmployee_Id(UUID id);
}
