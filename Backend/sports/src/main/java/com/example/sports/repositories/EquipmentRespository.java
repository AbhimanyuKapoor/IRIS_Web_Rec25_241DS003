package com.example.sports.repositories;

import com.example.sports.domain.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRespository extends JpaRepository<Equipment, UUID> {

}
