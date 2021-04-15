package com.exadel.backendservice.repository;

import com.exadel.backendservice.AbstractTestConfig;
import com.exadel.backendservice.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
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
        var list = repository.findAll();
        assertEquals(3, list.size());
    }

}