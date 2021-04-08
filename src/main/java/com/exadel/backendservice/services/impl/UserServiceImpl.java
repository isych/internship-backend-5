package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.RoleDto;
import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.entity.User;
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
        if (userEntityRepository.findByLogin(registrationRequest.getLogin()) == null) {
            Role userRole;
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
            User user = new User();
            user.setLogin(registrationRequest.getLogin());
            user.setRoleEntity(userRole);
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEmail(registrationRequest.getEmail());
            user.setFio(registrationRequest.getFio());
            userEntityRepository.save(user);
            result = true;
            LOGGER.info("User created");
        }
        return result;
    }

    private static List<UserDtoWithId> modifyUserEntityToUserDtoWithId(List<User> userList){
        return userList.stream()
                .map(elem -> new UserDtoWithId(elem.getId(), elem.getFio(), elem.getRoleEntity().getName().substring(5), elem.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public User findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
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
