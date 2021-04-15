package com.exadel.backendservice.repository;

import com.exadel.backendservice.ApplicationTestPropertyValues;
import com.exadel.backendservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class UserEntityRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.6-alpine");

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        ApplicationTestPropertyValues.populateRegistryFromPostgresContainer(registry, postgreSQLContainer);
    }

    @Autowired
    private UserEntityRepository repository;

    @Test
    void findByLogin() {
        User userFromDb = repository.findByLogin("test1-superadmin");
        assertEquals(999990, userFromDb.getId());
    }

    @Test
    void findAll() {
        var list = repository.findAll();
        assertEquals(11, list.size());
    }

    @Test
    void findAllByRoleEntity_Name() {
        var list = repository.findAllByRoleEntity_Name("ROLE_ADMIN");
        assertEquals(4, list.size());
    }

}