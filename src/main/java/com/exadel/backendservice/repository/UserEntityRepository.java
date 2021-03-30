package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);
    List<UserEntity> findAll();
}