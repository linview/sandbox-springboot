package com.devops.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artifact_types")
public class ArtifactType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(name = "file_extension")
    private String fileExtension;
    
    // Common artifact types: JAR, WAR, DOCKER, NPM, MAVEN
    public enum TypeName {
        JAR, WAR, DOCKER, NPM, MAVEN, PYPI, NUGET
    }
    
    // Constructors
    public ArtifactType() {}
    
    public ArtifactType(String name, String description, String fileExtension) {
        this.name = name;
        this.description = description;
        this.fileExtension = fileExtension;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFileExtension() { return fileExtension; }
    public void setFileExtension(String fileExtension) { this.fileExtension = fileExtension; }
}