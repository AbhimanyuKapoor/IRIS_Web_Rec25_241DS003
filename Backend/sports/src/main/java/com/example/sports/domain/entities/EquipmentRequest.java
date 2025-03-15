package com.example.sports.domain.entities;

import jakarta.persistence.*;

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

    // User & EquipmentRequest Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Equipment & EquipmentRequest Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    public EquipmentRequest() {
    }

    public EquipmentRequest(UUID id, String comments, RequestStatus requestStatus, String instructions, User user, Equipment equipment) {
        this.id = id;
        this.comments = comments;
        this.requestStatus = requestStatus;
        this.instructions = instructions;
        this.user = user;
        this.equipment = equipment;
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
        return Objects.equals(id, that.id) && requestStatus == that.requestStatus && Objects.equals(comments, that.comments) && Objects.equals(instructions, that.instructions) && Objects.equals(user, that.user) && Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestStatus, comments, instructions, user, equipment);
    }

    @Override
    public String toString() {
        return "EquipmentRequest{" +
                "id=" + id +
                ", requestStatus=" + requestStatus +
                ", comments='" + comments + '\'' +
                ", instructions='" + instructions + '\'' +
                ", user=" + user +
                ", equipment=" + equipment +
                '}';
    }
}
