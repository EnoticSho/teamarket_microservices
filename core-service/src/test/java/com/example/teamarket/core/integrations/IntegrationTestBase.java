package com.example.teamarket.core.integrations;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class IntegrationTestBase {

    private final static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

    @BeforeAll
    static void runContainer() {
        container.start();
    }


    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    @AfterAll
    static void stopContainer() {
        container.stop();
    }
}
