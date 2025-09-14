package com.devops.platform.repository;

import com.devops.platform.entity.Artifact;
import com.devops.platform.entity.ArtifactType;
import com.devops.platform.entity.Build;
import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    
    List<Artifact> findByBuild(Build build);
    
    List<Artifact> findByType(ArtifactType type);
    
    Optional<Artifact> findByArtifactIdAndVersion(String artifactId, String version);
    
    @Query("SELECT a FROM Artifact a WHERE a.build.project = :project")
    List<Artifact> findByProject(@Param("project") Project project);
    
    @Query("SELECT a FROM Artifact a WHERE a.build.project = :project AND a.type = :type")
    List<Artifact> findByProjectAndType(@Param("project") Project project, 
                                       @Param("type") ArtifactType type);
    
    @Query("SELECT a FROM Artifact a WHERE a.artifactId = :artifactId ORDER BY a.createdAt DESC")
    List<Artifact> findByArtifactId(@Param("artifactId") String artifactId);
    
    @Query("SELECT a FROM Artifact a WHERE a.build.project = :project AND " +
           "(LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(a.artifactId) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Artifact> searchByProjectAndKeyword(@Param("project") Project project, 
                                            @Param("keyword") String keyword);
    
    @Query("SELECT COUNT(a) FROM Artifact a WHERE a.build.project = :project")
    Long countByProject(@Param("project") Project project);
}