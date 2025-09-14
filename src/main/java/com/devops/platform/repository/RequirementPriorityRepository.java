package com.devops.platform.repository;

import com.devops.platform.entity.RequirementPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequirementPriorityRepository extends JpaRepository<RequirementPriority, Long> {
    Optional<RequirementPriority> findByName(String name);
    boolean existsByName(String name);
}