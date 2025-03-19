package com.example.sports.repositories;

import com.example.sports.domain.entities.InfrastructureRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InfrastructureRequestRepository extends JpaRepository<InfrastructureRequest, UUID> {

    Optional<InfrastructureRequest> findByInfrastructureId(UUID infrastructureId);
}git add -
