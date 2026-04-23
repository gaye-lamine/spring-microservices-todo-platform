package com.example.todo.infrastructure.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configures Flyway for database migrations.
 * This class initializes Flyway with the provided DataSource to manage database schema migrations.
 */
@Configuration
public class FlywayConfig {

    /**
     * Creates a Flyway bean for managing database migrations.
     * 
     * @param dataSource the DataSource used by Flyway to connect to the database
     * @return a Flyway instance configured with the provided DataSource
     */
    @Bean
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .load();
    }
}