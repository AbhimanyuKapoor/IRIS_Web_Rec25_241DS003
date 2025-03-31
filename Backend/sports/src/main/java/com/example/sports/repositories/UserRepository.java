package com.example.sports.repositories;

import com.example.sports.domain.entities.Role;
import com.example.sports.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    // To determine if Admin already exists in DB
    boolean existsByRole(Role role);
}
