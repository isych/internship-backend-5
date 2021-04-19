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
}