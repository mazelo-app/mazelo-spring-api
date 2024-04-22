package com.api.mazelo.type;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum StatusType {
    ACTIVE,
    IN_ACTIVE;

    @JsonCreator
    public static StatusType stringToType(final String code) {
        return Stream.of(StatusType.values())
                .filter(targetEnum -> targetEnum.name().equals(code))
                .findFirst()
                .orElse(null);
    }
}
