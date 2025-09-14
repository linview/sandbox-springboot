package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "builds")
public class Build {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String buildNumber;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(name = "commit_sha")
    private String commitSha;
    
    @Column(name = "branch_name")
    private String branchName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BuildStatus status;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "duration_seconds")
    private Long durationSeconds;
    
    @Column(columnDefinition = "TEXT")
    private String logs;
    
    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL)
    private List<Artifact> artifacts = new ArrayList<>();
    
    public enum BuildStatus {
        PENDING, RUNNING, SUCCESS, FAILED, CANCELLED
    }
    
    // Constructors
    public Build() {}
    
    public Build(String buildNumber, Project project, BuildStatus status) {
        this.buildNumber = buildNumber;
        this.project = project;
        this.status = status;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBuildNumber() { return buildNumber; }
    public void setBuildNumber(String buildNumber) { this.buildNumber = buildNumber; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public String getCommitSha() { return commitSha; }
    public void setCommitSha(String commitSha) { this.commitSha = commitSha; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public BuildStatus getStatus() { return status; }
    public void setStatus(BuildStatus status) { this.status = status; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public Long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Long durationSeconds) { this.durationSeconds = durationSeconds; }
    public String getLogs() { return logs; }
    public void setLogs(String logs) { this.logs = logs; }
    public List<Artifact> getArtifacts() { return artifacts; }
    public void setArtifacts(List<Artifact> artifacts) { this.artifacts = artifacts; }
}