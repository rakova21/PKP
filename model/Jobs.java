package com.crewrisk.model;

import com.crewrisk.model.enums.JobStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Jobs implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int exp;
    private int salary;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vacancy> vacancies;

    public Jobs(String name, int exp, int salary) {
        this.name = name;
        this.exp = exp;
        this.salary = salary;
        this.status = JobStatus.OPEN;
    }
}
