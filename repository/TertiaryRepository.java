package com.crewrisk.repository;

import com.crewrisk.model.Tertiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TertiaryRepository extends JpaRepository<Tertiary, Long> {
}
