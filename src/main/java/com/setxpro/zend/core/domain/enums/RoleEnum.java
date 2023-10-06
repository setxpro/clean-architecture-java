package com.setxpro.zend.core.domain.enums;

public enum RoleEnum {
    ADMIN("admin"),
    DEVELOPER("developer"),
    USER("user");

    private final String role;
    RoleEnum(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }

}
