package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID> {
}
