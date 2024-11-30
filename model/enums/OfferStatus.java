package com.crewrisk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OfferStatus {
    WAITING("В ожидании"),
    APPROVED("Одобрено"),
    REJECTED("Отклонено"),
    ;
    private final String name;
}

