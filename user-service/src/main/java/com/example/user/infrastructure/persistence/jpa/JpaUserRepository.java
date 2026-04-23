package com.example.user.infrastructure.persistence.jpa;

import com.example.user.domain.entities.User;
import com.example.user.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implements the UserRepository interface using JPA for data access.
 * This repository provides methods for CRUD operations on User entities.
 */
@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}