package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Education {
    HIGH("Высшее"),
    MID("Среднее"),
    ;
    private final String name;
}

