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
public class Certification implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer6;
    private String answer7;
    private String answer8;
    private String answer9;
    private String answer10;
    private boolean answer11;
    private boolean answer12;


    public Certification(String answer1, String answer2, String answer3, String answer4, String answer5, String answer6, String answer7, String answer8, String answer9, String answer10, boolean answer11, boolean answer12) {
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

    public float getScore() {
        float res = 0;
        res += getScore1();
        res += getScore2();
        res += getScore3();
        res += getScore4();
        res += getScore5();
        res += getScore6();
        res += getScore7();
        res += getScore8();
        res += getScore9();
        res += getScore10();
        res += getScore11();
        res += getScore12();
        return res;
    }

    public float getScore1() {
        if (answer1.equalsIgnoreCase("Application Programming Interface")) {
            return 1;
        }
        return 0;
    }

    public float getScore2() {
        if (answer2.equalsIgnoreCase("SMTP")) {
            return 1;
        }
        return 0;
    }

    public float getScore3() {
        if (answer3.equalsIgnoreCase("HyperText Markup Language")) {
            return 1;
        }
        return 0;
    }

    public float getScore4() {
        if (answer4.equalsIgnoreCase("Компиляция")) {
            return 0.5f;
        }
        return 0;
    }

    public float getScore5() {
        if (answer5.equalsIgnoreCase("Виртуализация")) {
            return 1;
        }
        return 0;
    }

    public float getScore6() {
        if (answer6.equalsIgnoreCase("Structured Query Language")) {
            return 1;
        }
        return 0;
    }

    public float getScore7() {
        if (answer7.equalsIgnoreCase("Agile")) {
            return 1;
        }
        return 0;
    }

    public float getScore8() {
        if (answer8.equalsIgnoreCase("Отладка")) {
            return 0.5f;
        }
        return 0;
    }

    public float getScore9() {
        if (answer9.equalsIgnoreCase("Uniform Resource Locator")) {
            return 1;
        }
        return 0;
    }

    public float getScore10() {
        if (answer10.equalsIgnoreCase("Сериализация")) {
            return 1;
        }
        return 0;
    }

    public float getScore11() {
        if (answer11) {
            return 0.5f;
        }
        return 0;
    }

    public float getScore12() {
        if (answer12) {
            return 0;
        }
        return 0.5f;
    }
}
