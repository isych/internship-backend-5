package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
}
