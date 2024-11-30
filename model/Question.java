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
public class Question implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int answer1;
    private int answer2;
    private String answer3;
    private String answer4;
    private int answer5;
    private int answer6;
    private String answer7;
    private int answer8;
    private String answer9;
    private String answer10;
    private String answer11;
    private String answer12;

    public Question(int answer1, int answer2, String answer3, String answer4, int answer5, int answer6, String answer7, int answer8, String answer9, String answer10, String answer11, String answer12) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.answer8 = answer8;
        this.answer9 = answer9;
        this.answer10 = answer10;
        this.answer11 = answer11;
        this.answer12 = answer12;
    }
}
