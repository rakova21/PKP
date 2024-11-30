package com.crewrisk.model;

import com.crewrisk.model.enums.VacancyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vacancy implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VacancyStatus status;

    @ManyToOne
    private Jobs job;
    @ManyToOne
    private Users user;

    public Vacancy(Jobs job, Users user) {
        this.job = job;
        this.user = user;
        this.status = VacancyStatus.WAITING;
    }
}
