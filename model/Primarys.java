package com.crewrisk.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Primarys implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String photo;
    private String surname;
    private String name;
    private String patronymic;
    private String passport;
    private String passport_number;

    public Primarys() {
        photo = "def.jpg";
        surname = "Фамилия";
        name = "Имя";
        patronymic = "Отчество";
        passport = "Серия паспорта";
        passport_number = "Номер паспорта";
    }

    public void set(String surname, String name, String patronymic, String passport, String passport_number) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.passport = passport;
        this.passport_number = passport_number;
    }

    public String getFio(){
        return surname + " " + name + " " + patronymic;
    }


}
