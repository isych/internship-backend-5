package com.exadel.backendservice;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

public class ApplicationTestPropertyValues {
    public static void populateRegistryFromContainers(DynamicPropertyRegistry registry,
                                                      PostgreSQLContainer<?> postgreSQLContainer) {
        populateRegistryFromPostgresContainer(registry, postgreSQLContainer);
    }

    public static void populateRegistryFromPostgresContainer(DynamicPropertyRegistry registry, PostgreSQLContainer<?> postgreSQLContainer) {
        String url = "classpath:db/migration";
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.flyway.locations", url::toString);
    }
}
