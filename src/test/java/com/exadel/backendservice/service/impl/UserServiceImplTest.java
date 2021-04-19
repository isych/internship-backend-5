package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.config.AbstractTestConfig;
import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.User;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends AbstractTestConfig {

    @Autowired
    UserService userService;

    @Test
    void findByLogin() {
        User user = userService.findByLogin("test3-user");
        assertEquals("Кулакова Регина Аркадьевна", user.getFio());
    }

    @Test
    void findByLoginAndPassword() {
        User user = userService.findByLoginAndPassword("test-admin", "1");
        assertEquals("Иванов Иван Иванович", user.getFio());
    }

    @Test
    void getAllUsers() {
        List<UserDtoWithId> users = userService.getAllUsers();
        assertEquals(11, users.size());
    }

    @Test
    void getAllAdmins() {
        List<UserDtoWithId> admins = userService.getAllAdmins();
        assertEquals(4, admins.size());
    }

    @Test
    void getAllTechSpec() {
        List<UserDtoWithId> tech = userService.getAllTechSpec();
        assertEquals(4, tech.size());
    }

    @Test
    void getAllSuperAdmins() {
        List<UserDtoWithId> superadmin = userService.getAllSuperAdmins();
        assertEquals(3, superadmin.size());
    }

    @Test
    void getListRoles() {
        List<String> response = userService.getListRoles();
        List<String> result = Arrays.asList("ADMIN", "TECH", "SUPERADMIN");
        assertEquals(result, response);
    }

    @Test
    void saveUser() {
        userService.saveUser(new RegistrationRequest("asafAFS", "ADSFADADS", "admin", "aafadf", "asdadfadf@adadfadsf.fd"));
        assertEquals(5, userService.getAllAdmins().size());
        assertEquals(12, userService.getAllUsers().size());

        /* Не корректно указана роль */
        userService.saveUser(new RegistrationRequest("dfghdgfhdfg", "gfdfhdfcvs", "adm", "xcvbdfghsgf", "sfasdgewrfd@adadfadsf.fd"));
        assertEquals(12, userService.getAllUsers().size());

        userService.saveUser(new RegistrationRequest("asdfadsd", "vbcbvxcvd", "tech", "xvbxcvbxcbv", "zcvaddasfadsf@adadfadsf.fd"));
        assertEquals(5, userService.getAllTechSpec().size());
        assertEquals(13, userService.getAllUsers().size());

        userService.saveUser(new RegistrationRequest("cvfsdsdfgsfdg", "cvbsdfsdfg", "superadmin", "cvdfadfsg", "uklkjghfdgfd@adadfadsf.fd"));
        assertEquals(4, userService.getAllSuperAdmins().size());
        assertEquals(14, userService.getAllUsers().size());
    }
}