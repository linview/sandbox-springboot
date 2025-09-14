package com.devops.platform.repository;

import com.devops.platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    Optional<Project> findByName(String name);
    
    List<Project> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT p FROM Project p WHERE p.repositoryUrl IS NOT NULL")
    List<Project> findAllWithRepository();
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Project> searchByKeyword(@Param("keyword") String keyword);
    
    boolean existsByName(String name);
}