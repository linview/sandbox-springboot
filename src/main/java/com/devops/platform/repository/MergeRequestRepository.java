package com.devops.platform.repository;

import com.devops.platform.entity.MergeRequest;
import com.devops.platform.entity.MRStatus;
import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MergeRequestRepository extends JpaRepository<MergeRequest, Long> {
    
    List<MergeRequest> findByProject(Project project);
    
    List<MergeRequest> findByProjectAndStatus(Project project, MRStatus status);
    
    List<MergeRequest> findByStatus(MRStatus status);
    
    @Query("SELECT mr FROM MergeRequest mr WHERE mr.project = :project AND " +
           "(LOWER(mr.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(mr.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<MergeRequest> searchByProjectAndKeyword(@Param("project") Project project, 
                                                @Param("keyword") String keyword);
    
    @Query("SELECT mr FROM MergeRequest mr WHERE mr.sourceBranch = :branch OR mr.targetBranch = :branch")
    List<MergeRequest> findByBranch(@Param("branch") String branch);
    
    @Query("SELECT COUNT(mr) FROM MergeRequest mr WHERE mr.project = :project")
    Long countByProject(@Param("project") Project project);
    
    @Query("SELECT COUNT(mr) FROM MergeRequest mr WHERE mr.project = :project AND mr.status = :status")
    Long countByProjectAndStatus(@Param("project") Project project, 
                                @Param("status") MRStatus status);
}