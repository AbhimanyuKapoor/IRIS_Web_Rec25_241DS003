package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "infrastructures")
public class Infrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "operating_hrs")
    private String operatingHrs;

    // User & InfrastructureRequest Relationship
    @OneToMany(mappedBy = "infrastructure", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<InfrastructureRequest> infrastructureRequests;

    public Infrastructure() {
    }

    public Infrastructure(UUID id, String name, String location, AvailabilityStatus availabilityStatus, Integer quantity, Integer capacity, String operatingHrs, List<InfrastructureRequest> infrastructureRequests) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
        this.quantity = quantity;
        this.capacity = capacity;
        this.operatingHrs = operatingHrs;
        this.infrastructureRequests = infrastructureRequests;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOperatingHrs() {
        return operatingHrs;
    }

    public void setOperatingHrs(String operatingHrs) {
        this.operatingHrs = operatingHrs;
    }

    public List<InfrastructureRequest> getInfrastructureRequests() {
        return infrastructureRequests;
    }

    public void setInfrastructureRequests(List<InfrastructureRequest> infrastructureRequests) {
        this.infrastructureRequests = infrastructureRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infrastructure that = (Infrastructure) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && availabilityStatus == that.availabilityStatus && Objects.equals(capacity, that.capacity) && Objects.equals(quantity, that.quantity) && Objects.equals(operatingHrs, that.operatingHrs) && Objects.equals(infrastructureRequests, that.infrastructureRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, availabilityStatus, capacity, quantity, operatingHrs, infrastructureRequests);
    }

    @Override
    public String toString() {
        return "Infrastructure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", capacity=" + capacity +
                ", quantity=" + quantity +
                ", operatingHrs='" + operatingHrs + '\'' +
                ", infrastructureRequests=" + infrastructureRequests +
                '}';
    }
}
