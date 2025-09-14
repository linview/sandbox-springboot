package com.devops.platform.repository;

import com.devops.platform.entity.Requirement;
import com.devops.platform.entity.RequirementStatus;
import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    
    List<Requirement> findByProject(Project project);
    
    List<Requirement> findByProjectAndStatus(Project project, RequirementStatus status);
    
    List<Requirement> findByStatus(RequirementStatus status);
    
    @Query("SELECT r FROM Requirement r WHERE r.project = :project AND " +
           "(LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Requirement> searchByProjectAndKeyword(@Param("project") Project project, 
                                               @Param("keyword") String keyword);
    
    @Query("SELECT COUNT(r) FROM Requirement r WHERE r.project = :project")
    Long countByProject(@Param("project") Project project);
    
    @Query("SELECT COUNT(r) FROM Requirement r WHERE r.project = :project AND r.status = :status")
    Long countByProjectAndStatus(@Param("project") Project project, 
                                @Param("status") RequirementStatus status);
}