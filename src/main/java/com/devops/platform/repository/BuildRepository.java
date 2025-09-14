package com.devops.platform.repository;

import com.devops.platform.entity.Build;
import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildRepository extends JpaRepository<Build, Long> {
    
    List<Build> findByProject(Project project);
    
    List<Build> findByProjectOrderByStartTimeDesc(Project project);
    
    @Query("SELECT b FROM Build b WHERE b.project = :project AND b.status = 'SUCCESS' " +
           "ORDER BY b.startTime DESC")
    List<Build> findSuccessfulBuildsByProject(@Param("project") Project project);
    
    @Query("SELECT b FROM Build b WHERE b.project = :project AND b.branchName = :branchName " +
           "ORDER BY b.startTime DESC")
    List<Build> findByProjectAndBranch(@Param("project") Project project, 
                                      @Param("branchName") String branchName);
    
    @Query("SELECT b FROM Build b WHERE b.project = :project AND b.commitSha = :commitSha")
    List<Build> findByProjectAndCommit(@Param("project") Project project, 
                                      @Param("commitSha") String commitSha);
    
    @Query("SELECT COUNT(b) FROM Build b WHERE b.project = :project")
    Long countByProject(@Param("project") Project project);
    
    @Query("SELECT COUNT(b) FROM Build b WHERE b.project = :project AND b.status = :status")
    Long countByProjectAndStatus(@Param("project") Project project, 
                                @Param("status") Build.BuildStatus status);
}