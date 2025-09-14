package com.devops.platform.repository;

import com.devops.platform.entity.ArtifactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtifactTypeRepository extends JpaRepository<ArtifactType, Long> {
    Optional<ArtifactType> findByName(String name);
    boolean existsByName(String name);
}