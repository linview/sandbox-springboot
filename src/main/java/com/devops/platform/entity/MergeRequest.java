package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merge_requests")
public class MergeRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotBlank
    @Column(name = "source_branch", nullable = false)
    private String sourceBranch;
    
    @NotBlank
    @Column(name = "target_branch", nullable = false)
    private String targetBranch;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private MRStatus status;
    
    @Column(name = "merge_commit_sha")
    private String mergeCommitSha;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "merged_at")
    private LocalDateTime mergedAt;
    
    @Column(name = "closed_at")
    private LocalDateTime closedAt;
    
    @OneToMany(mappedBy = "mergeRequest", cascade = CascadeType.ALL)
    private List<CodeReview> codeReviews = new ArrayList<>();
    
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
    public MergeRequest() {}
    
    public MergeRequest(String title, String description, String sourceBranch, 
                       String targetBranch, Project project, MRStatus status) {
        this.title = title;
        this.description = description;
        this.sourceBranch = sourceBranch;
        this.targetBranch = targetBranch;
        this.project = project;
        this.status = status;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSourceBranch() { return sourceBranch; }
    public void setSourceBranch(String sourceBranch) { this.sourceBranch = sourceBranch; }
    public String getTargetBranch() { return targetBranch; }
    public void setTargetBranch(String targetBranch) { this.targetBranch = targetBranch; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public MRStatus getStatus() { return status; }
    public void setStatus(MRStatus status) { this.status = status; }
    public String getMergeCommitSha() { return mergeCommitSha; }
    public void setMergeCommitSha(String mergeCommitSha) { this.mergeCommitSha = mergeCommitSha; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public LocalDateTime getMergedAt() { return mergedAt; }
    public void setMergedAt(LocalDateTime mergedAt) { this.mergedAt = mergedAt; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime closedAt) { this.closedAt = closedAt; }
    public List<CodeReview> getCodeReviews() { return codeReviews; }
    public void setCodeReviews(List<CodeReview> codeReviews) { this.codeReviews = codeReviews; }
}