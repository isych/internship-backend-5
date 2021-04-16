package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.model.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest extends AbstractTestConfig {

    @Autowired
    UserController userController;

    @Test
    void getAllRoles() {
        List<String> response = userController.getAllRoles();
        List<String> result = Arrays.asList("ADMIN", "TECH", "SUPERADMIN");
        assertEquals(result, response);
    }

    @Test
    void getAllUsers() {
        List<UserDtoWithId> users = userController.getAllUsers();
        assertEquals(11, users.size());
    }

    @Test
    void getAllAdmins() {
        List<UserDtoWithId> admins = userController.getAllAdmins();
        assertEquals(4, admins.size());
    }

    @Test
    void getAllTechSpec() {
        List<UserDtoWithId> tech = userController.getAllTechSpec();
        assertEquals(4, tech.size());
    }

    @Test
    void getAllSuperAdmins() {
        List<UserDtoWithId> superadmin = userController.getAllSuperAdmins();
        assertEquals(3, superadmin.size());
    }

    @Test
    void registerUser() {
        userController.registerUser(new RegistrationRequest("asafAFS", "ADSFADADS", "admin", "aafadf", "asdadfadf@adadfadsf.fd"));
        assertEquals(5, userController.getAllAdmins().size());
        assertEquals(12, userController.getAllUsers().size());

        /** Не корректно указана роль */
        userController.registerUser(new RegistrationRequest("dfghdgfhdfg", "gfdfhdfcvs", "adm", "xcvbdfghsgf", "sfasdgewrfd@adadfadsf.fd"));
        assertEquals(12, userController.getAllUsers().size());

        userController.registerUser(new RegistrationRequest("asdfadsd", "vbcbvxcvd", "tech", "xvbxcvbxcbv", "zcvaddasfadsf@adadfadsf.fd"));
        assertEquals(5, userController.getAllTechSpec().size());
        assertEquals(13, userController.getAllUsers().size());

        userController.registerUser(new RegistrationRequest("cvfsdsdfgsfdg", "cvbsdfsdfg", "superadmin", "cvdfadfsg", "uklkjghfdgfd@adadfadsf.fd"));
        assertEquals(4, userController.getAllSuperAdmins().size());
        assertEquals(14, userController.getAllUsers().size());

    }
}