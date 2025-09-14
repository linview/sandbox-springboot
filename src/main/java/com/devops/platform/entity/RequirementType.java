package com.devops.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "requirement_types")
public class RequirementType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    // Common types: STORY, BUG, TASK, EPIC
    public enum TypeName {
        STORY, BUG, TASK, EPIC
    }
    
    // Constructors
    public RequirementType() {}
    
    public RequirementType(String name, String description) {
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