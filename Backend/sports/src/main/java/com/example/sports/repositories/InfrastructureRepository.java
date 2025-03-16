package com.example.sports.repositories;

import com.example.sports.domain.entities.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfrastructureRepository extends JpaRepository<Infrastructure, UUID> {

}
