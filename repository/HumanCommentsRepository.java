package com.crewrisk.repository;

import com.crewrisk.model.HumanComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanCommentsRepository extends JpaRepository<HumanComments, Long> {
}
