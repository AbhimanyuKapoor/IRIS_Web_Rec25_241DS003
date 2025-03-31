package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
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

    // Operating Hours
    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

     // Infrastructure & InfrastructureRequest Relationship
     @OneToMany(mappedBy = "infrastructure", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
     private List<InfrastructureRequest> infrastructureRequests;

    public Infrastructure() {
    }

    public Infrastructure(UUID id, String name, String location, AvailabilityStatus availabilityStatus, Integer capacity, Integer quantity, LocalTime openingTime, LocalTime closingTime, List<InfrastructureRequest> infrastructureRequests) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
        this.capacity = capacity;
        this.quantity = quantity;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
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

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && availabilityStatus == that.availabilityStatus && Objects.equals(capacity, that.capacity) && Objects.equals(quantity, that.quantity) && Objects.equals(openingTime, that.openingTime) && Objects.equals(closingTime, that.closingTime) && Objects.equals(infrastructureRequests, that.infrastructureRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, availabilityStatus, capacity, quantity, openingTime, closingTime, infrastructureRequests);
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
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                '}';
    }
}
