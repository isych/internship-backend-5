package com.exadel.backendservice.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = BEFORE_CLASS)
public class AbstractTestConfig {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.6-alpine");


    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        ApplicationTestPropertyValues.populateRegistryFromPostgresContainer(registry, postgreSQLContainer);
    }

}
