package com.exadel.backendservice.repository;

import com.exadel.backendservice.config.AbstractTestConfig;
import com.exadel.backendservice.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleEntityRepositoryTest extends AbstractTestConfig {

    @Autowired
    private RoleEntityRepository repository;

    @Test
    void findByName() {
        Role role_admin = repository.findByName("ROLE_ADMIN");
        assertEquals(1, role_admin.getId());

        Role role_tech = repository.findByName("ROLE_TECH");
        assertEquals(2, role_tech.getId());

        Role role_superadmin = repository.findByName("ROLE_SUPERADMIN");
        assertEquals(3, role_superadmin.getId());
    }

    @Test
    void findAll() {
        var list1 = repository.findAll();
        assertEquals(3, list1.size());
    }
}