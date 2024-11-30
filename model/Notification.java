package com.crewrisk.model;

import com.crewrisk.controller.main.Main;
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
public class Notification implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date = Main.getDateNow();
    @Column(length = 5000)
    private String text;

    @ManyToOne
    private Users owner;

    public Notification(String text, Users owner) {
        this.text = text;
        this.owner = owner;
    }
}
