package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "condition")
    private String condition;

    // User & EquipmentRequest Relationship
    @OneToMany(mappedBy = "equipment", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<EquipmentRequest> equipmentRequests;

    public Equipment() {
    }

    public Equipment(UUID id, String name, String category, AvailabilityStatus availabilityStatus, Integer quantity, String condition, List<EquipmentRequest> equipmentRequests) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.availabilityStatus = availabilityStatus;
        this.quantity = quantity;
        this.condition = condition;
        this.equipmentRequests = equipmentRequests;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<EquipmentRequest> getEquipmentRequests() {
        return equipmentRequests;
    }

    public void setEquipmentRequests(List<EquipmentRequest> equipmentRequests) {
        this.equipmentRequests = equipmentRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) && Objects.equals(name, equipment.name) && Objects.equals(category, equipment.category) && availabilityStatus == equipment.availabilityStatus && Objects.equals(quantity, equipment.quantity) && Objects.equals(condition, equipment.condition) && Objects.equals(equipmentRequests, equipment.equipmentRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, availabilityStatus, quantity, condition, equipmentRequests);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", quantity=" + quantity +
                ", condition='" + condition + '\'' +
                ", equipmentRequests=" + equipmentRequests +
                '}';
    }
}
