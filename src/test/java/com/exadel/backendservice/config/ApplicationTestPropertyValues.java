package com.exadel.backendservice.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

public class ApplicationTestPropertyValues {
    public static void populateRegistryFromPostgresContainer(DynamicPropertyRegistry registry, PostgreSQLContainer<?> postgreSQLContainer) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.flyway.locations", "classpath:db/migration"::toString);
    }
}
