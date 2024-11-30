package com.crewrisk.model;

import com.crewrisk.model.enums.EventStatus;
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
public class Event implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String date;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public Event(String name, String date, EventStatus status) {
        this.name = name;
        this.date = date;
        this.status = status;
    }

    public void set(String name, String date, EventStatus status) {
        this.name = name;
        this.date = date;
        this.status = status;
    }
}
