package com.crewrisk.repository;

import com.crewrisk.model.AppAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAnswerRepository extends JpaRepository<AppAnswer, Long> {
}
