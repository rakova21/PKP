package com.crewrisk.repository;

import com.crewrisk.model.Apps;
import com.crewrisk.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppsRepository extends JpaRepository<Apps, Long> {
    List<Apps> findAllByOwner(Users owner);

}
