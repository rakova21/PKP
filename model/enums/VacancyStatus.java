package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VacancyStatus {
    WAITING("В ожидании"),
    APPROVED("Одобрено"),
    REJECTED("Отклонено"),
    ;
    private final String name;
}

