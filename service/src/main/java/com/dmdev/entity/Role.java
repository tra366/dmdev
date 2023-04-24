package com.dmdev.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    BUILDER;

    @Override
    public String getAuthority() {
        return name();
    }
}
