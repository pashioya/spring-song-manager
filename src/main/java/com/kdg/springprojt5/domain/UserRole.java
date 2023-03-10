package com.kdg.springprojt5.domain;

public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
