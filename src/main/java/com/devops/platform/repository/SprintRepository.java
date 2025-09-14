package com.devops.platform.repository;

import com.devops.platform.entity.Sprint;
import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByProject(Project project);
    
    @Query("SELECT s FROM Sprint s WHERE s.project = :project AND s.status = 'ACTIVE'")
    List<Sprint> findActiveSprintsByProject(@Param("project") Project project);
    
    @Query("SELECT COUNT(s) FROM Sprint s WHERE s.project = :project")
    Long countByProject(@Param("project") Project project);
}