package com.exadel.backendservice.services;

import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.UserEntity;
import com.exadel.backendservice.model.RegistrationRequest;

import java.util.List;

public interface UserService {
    UserEntity findByLogin(String login);
    UserEntity findByLoginAndPassword(String login, String password);
    Boolean saveUser(RegistrationRequest registrationRequest);
    List<UserDtoWithId> getAllUsers();
    List<UserDtoWithId> getAllAdmins();
    List<UserDtoWithId> getAllTechSpec();
    List<UserDtoWithId> getAllSuperAdmins();
}
