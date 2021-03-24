package com.exadel.backendservice.services;

import com.exadel.backendservice.entity.UserEntity;
import com.exadel.backendservice.model.RegistrationRequest;

public interface UserService {
    UserEntity findByLogin(String login);
    UserEntity findByLoginAndPassword(String login, String password);
    Boolean saveUser(RegistrationRequest registrationRequest);
}
