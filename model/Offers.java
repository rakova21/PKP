package com.crewrisk.model;

import com.crewrisk.model.enums.OfferStatus;
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
public class Offers implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String offer;

    @Enumerated(EnumType.STRING)
    private OfferStatus status = OfferStatus.WAITING;

    @ManyToOne
    private Users user;

    public Offers(Users user, String offer) {
        this.user = user;
        this.offer = offer;
    }
}
