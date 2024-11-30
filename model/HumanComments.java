package com.crewrisk.model;

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
public class HumanComments implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date;

    @Column(length = 5000)
    private String text;

    @ManyToOne
    private Users owner;
    @ManyToOne
    private Users user;

    public HumanComments(String text, String date, Users owner, Users user) {
        this.text = text;
        this.date = date;
        this.owner = owner;
        this.user = user;
    }
}
