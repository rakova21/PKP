package com.crewrisk.repository;

import com.crewrisk.model.Score;
import com.crewrisk.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    public List<Score> findAllByOwner_Role(Role role);
}
