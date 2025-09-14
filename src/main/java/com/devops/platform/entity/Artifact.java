package com.devops.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "artifacts")
public class Artifact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String artifactId; // Unique identifier for the artifact
    
    @NotBlank
    @Column(nullable = false)
    private String version;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ArtifactType type;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "build_id", nullable = false)
    private Build build;
    
    @Column(name = "file_path")
    private String filePath; // Storage location (Minio/Artifactory path)
    
    @Column(name = "file_size")
    private Long fileSize; // Size in bytes
    
    @Column(name = "checksum")
    private String checksum; // MD5/SHA checksum
    
    @Column(name = "download_url")
    private String downloadUrl;
    
    @Column(columnDefinition = "TEXT")
    private String metadata; // JSON metadata
    
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
    public Artifact() {}
    
    public Artifact(String name, String artifactId, String version, ArtifactType type, Build build) {
        this.name = name;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
        this.build = build;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArtifactId() { return artifactId; }
    public void setArtifactId(String artifactId) { this.artifactId = artifactId; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public ArtifactType getType() { return type; }
    public void setType(ArtifactType type) { this.type = type; }
    public Build getBuild() { return build; }
    public void setBuild(Build build) { this.build = build; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public String getChecksum() { return checksum; }
    public void setChecksum(String checksum) { this.checksum = checksum; }
    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}