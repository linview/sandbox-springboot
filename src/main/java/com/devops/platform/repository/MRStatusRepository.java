package com.devops.platform.repository;

import com.devops.platform.entity.MRStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MRStatusRepository extends JpaRepository<MRStatus, Long> {
    Optional<MRStatus> findByName(String name);
    boolean existsByName(String name);
}