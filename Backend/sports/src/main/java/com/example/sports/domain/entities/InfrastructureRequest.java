package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "infrastructure_requests")
public class InfrastructureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @Column(name = "time_slot", nullable = false)
    private String timeSlot;

    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    // User & InfrastructureRequest Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // User & InfrastructureRequest Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infrastructure_id")
    private Infrastructure infrastructure;

    public InfrastructureRequest() {
    }

    public InfrastructureRequest(UUID id, LocalDateTime requestedAt, String timeSlot, RequestStatus requestStatus, User user, Infrastructure infrastructure) {
        this.id = id;
        this.requestedAt = requestedAt;
        this.timeSlot = timeSlot;
        this.requestStatus = requestStatus;
        this.user = user;
        this.infrastructure = infrastructure;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfrastructureRequest that = (InfrastructureRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(requestedAt, that.requestedAt) && Objects.equals(timeSlot, that.timeSlot) && requestStatus == that.requestStatus && Objects.equals(user, that.user) && Objects.equals(infrastructure, that.infrastructure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestedAt, timeSlot, requestStatus, user, infrastructure);
    }

    @Override
    public String toString() {
        return "InfrastructureRequest{" +
                "id=" + id +
                ", requestedAt=" + requestedAt +
                ", timeSlot='" + timeSlot + '\'' +
                ", requestStatus=" + requestStatus +
                ", user=" + user +
                ", infrastructure=" + infrastructure +
                '}';
    }
}
