package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findAllByCandidate_Id(Integer id);
    Optional<Interview> findById(Integer id);
}
