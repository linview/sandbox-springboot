package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_reviews")
public class CodeReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "merge_request_id", nullable = false)
    private MergeRequest mergeRequest;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;
    
    @Column(columnDefinition = "TEXT")
    private String comments;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status;
    
    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
    
    public enum ReviewStatus {
        PENDING, APPROVED, CHANGES_REQUESTED, COMMENTED
    }
    
    // Constructors
    public CodeReview() {}
    
    public CodeReview(MergeRequest mergeRequest, User reviewer, ReviewStatus status) {
        this.mergeRequest = mergeRequest;
        this.reviewer = reviewer;
        this.status = status;
    }
    
    @PrePersist
    protected void onCreate() {
        if (reviewedAt == null) {
            reviewedAt = LocalDateTime.now();
        }
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MergeRequest getMergeRequest() { return mergeRequest; }
    public void setMergeRequest(MergeRequest mergeRequest) { this.mergeRequest = mergeRequest; }
    public User getReviewer() { return reviewer; }
    public void setReviewer(User reviewer) { this.reviewer = reviewer; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public ReviewStatus getStatus() { return status; }
    public void setStatus(ReviewStatus status) { this.status = status; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }
}