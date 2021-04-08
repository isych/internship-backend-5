package com.exadel.backendservice.repository;


import com.exadel.backendservice.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {
}
