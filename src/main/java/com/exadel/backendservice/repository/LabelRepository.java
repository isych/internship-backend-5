package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Integer> {
}
