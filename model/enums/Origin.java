package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Origin {
    MINSK("Минск"),
    GRODNO("Гродно"),
    GOMEL("Гомель"),
    VITEBSK("Витебск"),
    MOGILEV("Могилёв"),
    OTHER("Другое");
    private final String name;
}

