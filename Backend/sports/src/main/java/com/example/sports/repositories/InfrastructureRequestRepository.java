package com.example.sports.repositories;

import com.example.sports.domain.entities.InfrastructureRequest;
import com.example.sports.domain.entities.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InfrastructureRequestRepository extends JpaRepository<InfrastructureRequest, UUID> {

    Optional<InfrastructureRequest> findByInfrastructureIdAndUserIdAndRequestedOn(UUID infrastructureId, UUID userId, LocalDate requestedOn);

    @Query("SELECT a FROM InfrastructureRequest a WHERE a.requestedOn = ?1 AND a.reminderSent = false AND a.requestStatus = ?2")
    List<InfrastructureRequest>  findByDateAndStatus(LocalDate date, RequestStatus requestStatus);
}
