package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Disability {
    NO("Нет"),
    I("1 группа"),
    II("2 группа"),
    III("3 группа");
    private final String name;
}

