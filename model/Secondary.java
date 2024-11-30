package com.crewrisk.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Secondary implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date;
    private String issued;
    private String issued_date;
    private String identity;
    private String address;
    private String tel_mob;
    private String tel_home;
    private String email;
    private String job;
    private String post;
    private int income;
    private int experience;

    public Secondary() {
        this.date = LocalDateTime.now().toString().substring(0, 10);
        this.issued = "Кем выдан";
        this.issued_date = LocalDateTime.now().toString().substring(0, 10);
        this.identity = "Идентификационный номер";
        this.address = "Адрес";
        this.tel_mob = "Мобильный телефон";
        this.tel_home = "Домашний номер";
        this.email = "Электронная почта";
        this.job = "Место работы";
        this.post = "Должность";
        this.income = 0;
        this.experience = 0;
    }

    public void set(String date, String issued, String issued_date, String identity, String address, String tel_mob, String tel_home, String email, String job, String post, int income, int experience) {
        this.date = date;
        this.issued = issued;
        this.issued_date = issued_date;
        this.identity = identity;
        this.address = address;
        this.tel_mob = tel_mob;
        this.tel_home = tel_home;
        this.email = email;
        this.job = job;
        this.post = post;
        this.income = income;
        this.experience = experience;
    }
}
