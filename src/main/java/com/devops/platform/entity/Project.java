package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "repository_url")
    private String repositoryUrl;
    
    @Column(name = "ci_config_path")
    private String ciConfigPath = ".gitlab-ci.yml";
    
    @Column(name = "default_branch")
    private String defaultBranch = "main";
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Requirement> requirements = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<MergeRequest> mergeRequests = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Build> builds = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Sprint> sprints = new ArrayList<>();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Project() {}
    
    public Project(String name, String description) {
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
    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }
    public String getCiConfigPath() { return ciConfigPath; }
    public void setCiConfigPath(String ciConfigPath) { this.ciConfigPath = ciConfigPath; }
    public String getDefaultBranch() { return defaultBranch; }
    public void setDefaultBranch(String defaultBranch) { this.defaultBranch = defaultBranch; }
    public List<Requirement> getRequirements() { return requirements; }
    public void setRequirements(List<Requirement> requirements) { this.requirements = requirements; }
    public List<MergeRequest> getMergeRequests() { return mergeRequests; }
    public void setMergeRequests(List<MergeRequest> mergeRequests) { this.mergeRequests = mergeRequests; }
    public List<Build> getBuilds() { return builds; }
    public void setBuilds(List<Build> builds) { this.builds = builds; }
    public List<Sprint> getSprints() { return sprints; }
    public void setSprints(List<Sprint> sprints) { this.sprints = sprints; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}