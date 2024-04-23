package com.api.mazelo.type;


import java.util.stream.Stream;

public enum UserRoleType {
    USER,
    ADMIN,
    SUPPORT;

    public String getName() {
        return this.name();
    }

    public static UserRoleType stringToType(final String typeStr) {
        return Stream.of(UserRoleType.values())
                .filter(targetEnum -> targetEnum.name().equals(typeStr))
                .findFirst()
                .orElse(null);
    }
}
