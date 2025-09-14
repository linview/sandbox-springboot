package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
    
    // Common roles: ADMIN, DEVELOPER, QA, DEVOPS, VIEWER
    public enum RoleName {
        ADMIN, DEVELOPER, QA, DEVOPS, VIEWER
    }
    
    // Constructors
    public Role() {}
    
    public Role(String name, String description) {
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
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}