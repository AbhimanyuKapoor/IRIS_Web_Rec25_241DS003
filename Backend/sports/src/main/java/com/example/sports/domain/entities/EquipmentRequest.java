package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "equipment_requests")
public class EquipmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @Column(name = "comments")
    private String comments;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "duration")
    private String duration;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    // User & EquipmentRequest Relationship
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // Equipment & EquipmentRequest Relationship
    @ManyToOne()
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    public EquipmentRequest() {
    }

    public EquipmentRequest(UUID id, RequestStatus requestStatus, String comments, String instructions, Integer quantity, String duration, LocalDateTime updated, User user, Equipment equipment) {
        this.id = id;
        this.requestStatus = requestStatus;
        this.comments = comments;
        this.instructions = instructions;
        this.quantity = quantity;
        this.duration = duration;
        this.updated = updated;
        this.user = user;
        this.equipment = equipment;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentRequest that = (EquipmentRequest) o;
        return Objects.equals(id, that.id) && requestStatus == that.requestStatus && Objects.equals(comments, that.comments) && Objects.equals(instructions, that.instructions) && Objects.equals(quantity, that.quantity) && Objects.equals(duration, that.duration) && Objects.equals(updated, that.updated) && Objects.equals(user, that.user) && Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestStatus, comments, instructions, quantity, duration, updated, user, equipment);
    }

    @Override
    public String toString() {
        return "EquipmentRequest{" +
                "id=" + id +
                ", requestStatus=" + requestStatus +
                ", comments='" + comments + '\'' +
                ", instructions='" + instructions + '\'' +
                ", quantity=" + quantity +
                ", duration='" + duration + '\'' +
                ", updated=" + updated +
                ", user=" + user.getId() +
                ", equipment=" + equipment.getId() +
                '}';
    }
}
