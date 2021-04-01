package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.RoleDto;
import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.RoleEntity;
import com.exadel.backendservice.entity.UserEntity;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.repository.UserEntityRepository;
import com.exadel.backendservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
            RoleEntity userRole;
            if (registrationRequest.getRole().toLowerCase().trim().equals("tech")) {
                userRole = roleEntityRepository.findByName("ROLE_TECH");
            } else if (registrationRequest.getRole().toLowerCase().trim().equals("admin")) {
                userRole = roleEntityRepository.findByName("ROLE_ADMIN");
            } else if (registrationRequest.getRole().toLowerCase().trim().equals("superadmin")) {
                userRole = roleEntityRepository.findByName("ROLE_SUPERADMIN");
            } else {
                LOGGER.info("User not created. Role specified incorrectly.");
                return false;
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(registrationRequest.getLogin());
            userEntity.setRoleEntity(userRole);
            userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userEntity.setEmail(registrationRequest.getEmail());
            userEntity.setFio(registrationRequest.getFio());
            userEntityRepository.save(userEntity);
            result = true;
            LOGGER.info("User created");
        }
        return result;
    }

    private static List<UserDtoWithId> modifyUserEntityToUserDtoWithId(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(elem -> new UserDtoWithId(elem.getId(), elem.getFio(), elem.getRoleEntity().getName().substring(5), elem.getEmail()))
                .collect(Collectors.toList());
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

    @Override
    public List<UserDtoWithId> getAllUsers(){
        return modifyUserEntityToUserDtoWithId(userEntityRepository.findAll());
    }

    @Override
    public List<UserDtoWithId> getAllAdmins() {
        return modifyUserEntityToUserDtoWithId(userEntityRepository.findAllByRoleEntity_Name("ROLE_ADMIN"));
    }

    @Override
    public List<UserDtoWithId> getAllTechSpec() {
        return modifyUserEntityToUserDtoWithId(userEntityRepository.findAllByRoleEntity_Name("ROLE_TECH"));
    }

    @Override
    public List<UserDtoWithId> getAllSuperAdmins() {
        return modifyUserEntityToUserDtoWithId(userEntityRepository.findAllByRoleEntity_Name("ROLE_SUPERADMIN"));
    }

    @Override
    public List<RoleDto> getListRoles() {
        return roleEntityRepository.findAll().stream()
                .map(elem -> new RoleDto(elem.getName().substring(5)))
                .collect(Collectors.toList());
    }
}
