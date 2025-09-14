package com.devops.platform.repository;

import com.devops.platform.entity.RequirementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequirementTypeRepository extends JpaRepository<RequirementType, Long> {
    Optional<RequirementType> findByName(String name);
    boolean existsByName(String name);
}