package com.crewrisk.model;

import com.crewrisk.model.enums.Citizenship;
import com.crewrisk.model.enums.Education;
import com.crewrisk.model.enums.Marital;
import com.crewrisk.model.enums.Origin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Tertiary implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Marital marital;
    @Enumerated(EnumType.STRING)
    private Origin origin;
    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;
    @Enumerated(EnumType.STRING)
    private Education education;

    public Tertiary() {
        marital = Marital.SINGLE;
        origin = Origin.MINSK;
        citizenship = Citizenship.BELARUS;
        education = Education.MID;
    }

    public void set(Marital marital, Origin origin, Citizenship citizenship, Education education) {
        this.marital = marital;
        this.origin = origin;
        this.citizenship = citizenship;
        this.education = education;
    }
}
