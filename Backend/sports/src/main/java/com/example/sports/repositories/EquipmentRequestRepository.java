package com.example.sports.repositories;

import com.example.sports.domain.entities.EquipmentRequest;
import com.example.sports.domain.entities.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EquipmentRequestRepository extends JpaRepository<EquipmentRequest, UUID> {

    // For Admin to view Pending Requests (Oldest to Newest: Ascending)
    List<EquipmentRequest>  findByRequestStatusOrderByUpdatedAsc(RequestStatus requestStatus);

    // For a user to view his requests (Newest to Oldest)
    List<EquipmentRequest> findByUserIdOrderByUpdatedDesc(UUID userId);
}
