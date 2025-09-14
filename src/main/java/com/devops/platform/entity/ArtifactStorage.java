package com.devops.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artifact_storage")
public class ArtifactStorage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name; // Storage name (e.g., "minio-primary", "artifactory-prod")
    
    @Column(nullable = false)
    private String type; // MINIO, ARTIFACTORY, S3, etc.
    
    @Column(name = "endpoint_url", nullable = false)
    private String endpointUrl;
    
    @Column(name = "bucket_name")
    private String bucketName;
    
    @Column(name = "repository_name")
    private String repositoryName;
    
    @Column(name = "access_key")
    private String accessKey;
    
    @Column(name = "secret_key")
    private String secretKey;
    
    @Column(name = "is_default")
    private Boolean isDefault = false;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // Constructors
    public ArtifactStorage() {}
    
    public ArtifactStorage(String name, String type, String endpointUrl) {
        this.name = name;
        this.type = type;
        this.endpointUrl = endpointUrl;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getEndpointUrl() { return endpointUrl; }
    public void setEndpointUrl(String endpointUrl) { this.endpointUrl = endpointUrl; }
    public String getBucketName() { return bucketName; }
    public void setBucketName(String bucketName) { this.bucketName = bucketName; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public String getAccessKey() { return accessKey; }
    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }
    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}