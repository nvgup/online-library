package com.onlinelibrary.security;

public enum UserRole {

    ADMIN("admin"), COMMON_USER("common_user");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
