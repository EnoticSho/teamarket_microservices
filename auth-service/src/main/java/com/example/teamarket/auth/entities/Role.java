package com.example.teamarket.auth.entities;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public static Role valueOf(int id) {
        for (Role role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role found for id: " + id);
    }
}
