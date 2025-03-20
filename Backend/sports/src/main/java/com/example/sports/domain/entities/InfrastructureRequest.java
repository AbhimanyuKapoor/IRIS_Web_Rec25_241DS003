package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "infrastructure_requests")
public class InfrastructureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "requested_for", nullable = false)
    private LocalTime requestedFor;

    @Column(name = "requested_on", nullable = false)
    private LocalDate requestedOn;

    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @Column(name = "reminder_sent", nullable = false)
    private Boolean reminderSent;

    // User & InfrastructureRequest Relationship
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // Infrastructure & InfrastructureRequest Relationship
    @ManyToOne()
    @JoinColumn(name = "infrastructure_id")
    private Infrastructure infrastructure;

    public InfrastructureRequest() {
    }

    public InfrastructureRequest(UUID id, LocalTime requestedFor, LocalDate requestedOn, RequestStatus requestStatus, Boolean reminderSent, User user, Infrastructure infrastructure) {
        this.id = id;
        this.requestedFor = requestedFor;
        this.requestedOn = requestedOn;
        this.requestStatus = requestStatus;
        this.reminderSent = reminderSent;
        this.user = user;
        this.infrastructure = infrastructure;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalTime getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(LocalTime requestedFor) {
        this.requestedFor = requestedFor;
    }

    public LocalDate getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(LocalDate requestedOn) {
        this.requestedOn = requestedOn;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Boolean getReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(Boolean reminderSent) {
        this.reminderSent = reminderSent;
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
        return Objects.equals(id, that.id) && Objects.equals(requestedFor, that.requestedFor) && Objects.equals(requestedOn, that.requestedOn) && requestStatus == that.requestStatus && Objects.equals(reminderSent, that.reminderSent) && Objects.equals(user, that.user) && Objects.equals(infrastructure, that.infrastructure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestedFor, requestedOn, requestStatus, reminderSent, user, infrastructure);
    }

    @Override
    public String toString() {
        return "InfrastructureRequest{" +
                "id=" + id +
                ", requestedFor=" + requestedFor +
                ", requestedOn=" + requestedOn +
                ", requestStatus=" + requestStatus +
                ", reminderSent=" + reminderSent +
                ", user=" + user +
                ", infrastructure=" + infrastructure +
                '}';
    }
}
