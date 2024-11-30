package com.crewrisk.repository;

import com.crewrisk.model.Users;
import com.crewrisk.model.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    List<Users> findAllByOrderByRole();

    List<Users> findAllByRoleAndTertiary_MaritalAndTertiary_OriginAndTertiary_Citizenship(Role role, Marital marital, Origin origin, Citizenship citizenship);

    List<Users> findAllByRole(Role role);

    List<Users> findAllByTertiary_Marital(Marital marital);

    List<Users> findAllByTertiary_Origin(Origin origin);

    List<Users> findAllByTertiary_Citizenship(Citizenship citizenship);

    List<Users> findAllByTertiary_Education(Education education);

    List<Users> findAllByOrderBySecondary_Experience();
}
