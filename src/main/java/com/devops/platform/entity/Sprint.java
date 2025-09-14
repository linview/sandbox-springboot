package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sprints")
public class Sprint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String goal;
    
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Requirement> requirements = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SprintStatus status;
    
    public enum SprintStatus {
        PLANNING, ACTIVE, COMPLETED, CANCELLED
    }
    
    // Constructors
    public Sprint() {}
    
    public Sprint(String name, String goal, LocalDate startDate, LocalDate endDate, Project project) {
        this.name = name;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.status = SprintStatus.PLANNING;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public List<Requirement> getRequirements() { return requirements; }
    public void setRequirements(List<Requirement> requirements) { this.requirements = requirements; }
    public SprintStatus getStatus() { return status; }
    public void setStatus(SprintStatus status) { this.status = status; }
}