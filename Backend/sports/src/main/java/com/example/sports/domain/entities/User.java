package com.example.sports.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "branch")
    private String branch;

    @Column(name = "role", nullable = false)
    private Role role;

    // User & EquipmentRequest Relationship
    @OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<EquipmentRequest> equipmentRequests;

    // User & InfrastructureRequest Relationship
    @OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<InfrastructureRequest> infrastructureRequests;

    public User() {
    }

    public User(UUID id, String name, String email, String password, String branch, Role role, List<EquipmentRequest> equipmentRequests, List<InfrastructureRequest> infrastructureRequests) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.role = role;
        this.equipmentRequests = equipmentRequests;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<EquipmentRequest> getEquipmentRequests() {
        return equipmentRequests;
    }

    public void setEquipmentRequests(List<EquipmentRequest> equipmentRequests) {
        this.equipmentRequests = equipmentRequests;
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(branch, user.branch) && role == user.role && Objects.equals(equipmentRequests, user.equipmentRequests) && Objects.equals(infrastructureRequests, user.infrastructureRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, branch, role, equipmentRequests, infrastructureRequests);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", branch='" + branch + '\'' +
                ", role=" + role +
                ", equipmentRequests=" + equipmentRequests +
                ", infrastructureRequests=" + infrastructureRequests +
                '}';
    }
}
