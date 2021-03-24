package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.entity.RoleEntity;
import com.exadel.backendservice.entity.UserEntity;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.repository.UserEntityRepository;
import com.exadel.backendservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean saveUser(RegistrationRequest registrationRequest) {
        boolean result = false;
        if(userEntityRepository.findByLogin(registrationRequest.getLogin()) == null) {
            RoleEntity userRole = null;
            if (registrationRequest.getRole().toLowerCase().trim().equals("user")) {
                userRole = roleEntityRepository.findByName("ROLE_USER");
            } else if (registrationRequest.getRole().toLowerCase().trim().equals("admin")) {
                userRole = roleEntityRepository.findByName("ROLE_ADMIN");
            } else {
                return false;
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(registrationRequest.getLogin());
            userEntity.setRoleEntity(userRole);
            userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userEntityRepository.save(userEntity);
            result = true;
        }
        return result;
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    @Override
    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
