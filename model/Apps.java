package com.crewrisk.model;

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
public class Apps implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 5000)
    private String text;
    private String date;

    @ManyToOne
    private Users owner;
    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL)
    private List<AppAnswer> answers;

    public Apps(String text, String date, Users owner) {
        this.text = text;
        this.date = date;
        this.owner = owner;
    }
}
