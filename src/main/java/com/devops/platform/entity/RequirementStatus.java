package com.devops.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "requirement_statuses")
public class RequirementStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    // Common statuses: TODO, IN_PROGRESS, DONE, BLOCKED
    public enum StatusName {
        TODO, IN_PROGRESS, DONE, BLOCKED
    }
    
    // Constructors
    public RequirementStatus() {}
    
    public RequirementStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}