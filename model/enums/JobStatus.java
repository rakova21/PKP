package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobStatus {
    OPEN("Открыто"),
    CLOSE("Закрыто"),
    ;

    private final String name;
}

