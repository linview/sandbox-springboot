package com.devops.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "requirement_priorities")
public class RequirementPriority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    private Integer level; // For ordering (1=highest, 5=lowest)
    
    // Common priorities: CRITICAL, HIGH, MEDIUM, LOW
    public enum PriorityName {
        CRITICAL, HIGH, MEDIUM, LOW
    }
    
    // Constructors
    public RequirementPriority() {}
    
    public RequirementPriority(String name, String description, Integer level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
}