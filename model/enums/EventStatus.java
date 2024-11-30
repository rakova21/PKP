package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventStatus {
    TOBE("Предстоит"),
    PASSED("Прошло"),
    ;
    private final String name;
}

