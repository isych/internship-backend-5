package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    List<User> findAll();
    List<User> findAllByRoleEntity_Name(String roleName);
}