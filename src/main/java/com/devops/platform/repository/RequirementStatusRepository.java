package com.devops.platform.repository;

import com.devops.platform.entity.RequirementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequirementStatusRepository extends JpaRepository<RequirementStatus, Long> {
    Optional<RequirementStatus> findByName(String name);
    boolean existsByName(String name);
}