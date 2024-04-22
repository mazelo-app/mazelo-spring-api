package com.api.mazelo.configuration;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

public class AppUserDetails extends User {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long id;

    public AppUserDetails(Long id, String userName, String password, Set<SimpleGrantedAuthority> authorities) {
        super(userName, password, authorities);
        this.id = id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
