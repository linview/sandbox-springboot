package com.devops.platform.controller;

import com.devops.platform.entity.Artifact;
import com.devops.platform.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artifacts")
public class ArtifactController {
    
    @Autowired
    private ArtifactService artifactService;
    
    @PostMapping
    public ResponseEntity<Artifact> createArtifact(
            @RequestBody Artifact artifact,
            @RequestParam Long buildId,
            @RequestParam Long typeId) {
        
        try {
            Artifact createdArtifact = artifactService.createArtifact(artifact, buildId, typeId);
            return ResponseEntity.ok(createdArtifact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Artifact> updateArtifact(
            @PathVariable Long id, @RequestBody Artifact artifactDetails) {
        
        try {
            Artifact updatedArtifact = artifactService.updateArtifact(id, artifactDetails);
            return ResponseEntity.ok(updatedArtifact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtifact(@PathVariable Long id) {
        try {
            artifactService.deleteArtifact(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/build/{buildId}")
    public ResponseEntity<List<Artifact>> getArtifactsByBuild(@PathVariable Long buildId) {
        try {
            List<Artifact> artifacts = artifactService.getArtifactsByBuild(buildId);
            return ResponseEntity.ok(artifacts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Artifact>> getArtifactsByProject(@PathVariable Long projectId) {
        try {
            List<Artifact> artifacts = artifactService.getArtifactsByProject(projectId);
            return ResponseEntity.ok(artifacts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/type/{typeId}")
    public ResponseEntity<List<Artifact>> getArtifactsByProjectAndType(
            @PathVariable Long projectId, @PathVariable Long typeId) {
        
        try {
            List<Artifact> artifacts = artifactService.getArtifactsByProjectAndType(projectId, typeId);
            return ResponseEntity.ok(artifacts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Artifact> getArtifactById(@PathVariable Long id) {
        Optional<Artifact> artifact = artifactService.getArtifactById(id);
        return artifact.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/artifact/{artifactId}/version/{version}")
    public ResponseEntity<Artifact> getArtifactByIdAndVersion(
            @PathVariable String artifactId, @PathVariable String version) {
        
        Optional<Artifact> artifact = artifactService.getArtifactByIdAndVersion(artifactId, version);
        return artifact.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/artifact/{artifactId}")
    public ResponseEntity<List<Artifact>> getArtifactsByArtifactId(@PathVariable String artifactId) {
        List<Artifact> artifacts = artifactService.getArtifactsByArtifactId(artifactId);
        return ResponseEntity.ok(artifacts);
    }
    
    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Artifact>> getArtifactsByType(@PathVariable Long typeId) {
        try {
            List<Artifact> artifacts = artifactService.getArtifactsByType(typeId);
            return ResponseEntity.ok(artifacts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/count")
    public ResponseEntity<Long> getArtifactCountByProject(@PathVariable Long projectId) {
        try {
            Long count = artifactService.getArtifactCountByProject(projectId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}