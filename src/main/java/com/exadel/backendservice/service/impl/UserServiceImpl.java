package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.entity.User;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.repository.RoleEntityRepository;
import com.exadel.backendservice.repository.UserEntityRepository;
import com.exadel.backendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Boolean saveUser(RegistrationRequest registrationRequest) {
        boolean result = false;
        if (userEntityRepository.findByLogin(registrationRequest.getLogin()) == null) {
            Role userRole;
            switch (registrationRequest.getRole().toLowerCase().trim()) {
                case "tech":
                    userRole = roleEntityRepository.findByName("ROLE_TECH");
                    break;
                case "admin":
                    userRole = roleEntityRepository.findByName("ROLE_ADMIN");
                    break;
                case "superadmin":
                    userRole = roleEntityRepository.findByName("ROLE_SUPERADMIN");
                    break;
                default:
                    log.info("User not created. Role specified incorrectly.");
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
            log.info("User created");
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
    public List<String> getListRoles() {
        return roleEntityRepository.findAll().stream()
                .map(elem -> elem.getName().substring(5))
                .collect(Collectors.toList());
    }
}
