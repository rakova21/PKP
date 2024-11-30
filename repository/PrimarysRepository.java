package com.crewrisk.repository;

import com.crewrisk.model.Primarys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimarysRepository extends JpaRepository<Primarys, Long> {
}
