package com.crewrisk.repository;

import com.crewrisk.model.Secondary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryRepository extends JpaRepository<Secondary, Long> {
}
