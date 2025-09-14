package com.devops.platform.service;

import com.devops.platform.entity.Artifact;
import com.devops.platform.entity.ArtifactType;
import com.devops.platform.entity.Build;
import com.devops.platform.entity.Project;
import com.devops.platform.repository.ArtifactRepository;
import com.devops.platform.repository.ArtifactTypeRepository;
import com.devops.platform.repository.BuildRepository;
import com.devops.platform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtifactService {
    
    @Autowired
    private ArtifactRepository artifactRepository;
    
    @Autowired
    private BuildRepository buildRepository;
    
    @Autowired
    private ArtifactTypeRepository artifactTypeRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public Artifact createArtifact(Artifact artifact, Long buildId, Long typeId) {
        
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() -> new IllegalArgumentException("Build not found with id: " + buildId));
        
        ArtifactType type = artifactTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Artifact type not found with id: " + typeId));
        
        artifact.setBuild(build);
        artifact.setType(type);
        
        return artifactRepository.save(artifact);
    }
    
    public Artifact updateArtifact(Long id, Artifact artifactDetails) {
        Artifact artifact = artifactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artifact not found with id: " + id));
        
        artifact.setName(artifactDetails.getName());
        artifact.setFilePath(artifactDetails.getFilePath());
        artifact.setFileSize(artifactDetails.getFileSize());
        artifact.setChecksum(artifactDetails.getChecksum());
        artifact.setDownloadUrl(artifactDetails.getDownloadUrl());
        artifact.setMetadata(artifactDetails.getMetadata());
        
        return artifactRepository.save(artifact);
    }
    
    public void deleteArtifact(Long id) {
        Artifact artifact = artifactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artifact not found with id: " + id));
        artifactRepository.delete(artifact);
    }
    
    @Transactional(readOnly = true)
    public List<Artifact> getArtifactsByBuild(Long buildId) {
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() -> new IllegalArgumentException("Build not found with id: " + buildId));
        return artifactRepository.findByBuild(build);
    }
    
    @Transactional(readOnly = true)
    public List<Artifact> getArtifactsByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return artifactRepository.findByProject(project);
    }
    
    @Transactional(readOnly = true)
    public List<Artifact> getArtifactsByProjectAndType(Long projectId, Long typeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        ArtifactType type = artifactTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Artifact type not found with id: " + typeId));
        
        return artifactRepository.findByProjectAndType(project, type);
    }
    
    @Transactional(readOnly = true)
    public Optional<Artifact> getArtifactById(Long id) {
        return artifactRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Artifact> getArtifactByIdAndVersion(String artifactId, String version) {
        return artifactRepository.findByArtifactIdAndVersion(artifactId, version);
    }
    
    @Transactional(readOnly = true)
    public List<Artifact> getArtifactsByArtifactId(String artifactId) {
        return artifactRepository.findByArtifactId(artifactId);
    }
    
    @Transactional(readOnly = true)
    public List<Artifact> getArtifactsByType(Long typeId) {
        ArtifactType type = artifactTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Artifact type not found with id: " + typeId));
        return artifactRepository.findByType(type);
    }
    
    @Transactional(readOnly = true)
    public Long getArtifactCountByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return artifactRepository.countByProject(project);
    }
}