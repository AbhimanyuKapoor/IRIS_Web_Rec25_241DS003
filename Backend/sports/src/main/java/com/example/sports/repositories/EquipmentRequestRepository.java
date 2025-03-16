package com.example.sports.repositories;

import com.example.sports.domain.entities.EquipmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRequestRepository extends JpaRepository<EquipmentRequest, UUID> {

}
