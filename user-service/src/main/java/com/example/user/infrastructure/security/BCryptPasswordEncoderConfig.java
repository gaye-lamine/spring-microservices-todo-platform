package com.example.user.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures the BCrypt password encoder for secure password hashing.
 * This encoder is used for encoding user passwords before storing them in the database.
 * 
 * @return a PasswordEncoder instance for encoding passwords.
 */
@Configuration
public class BCryptPasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}