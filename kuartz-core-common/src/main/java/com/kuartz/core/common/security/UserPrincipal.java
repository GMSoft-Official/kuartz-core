package com.kuartz.core.common.security;

import java.util.List;
import java.util.Set;

public class UserPrincipal {

    private String username;

    private String email;

    private Set<PrincipalAuthority> authority;

    private List<PrincipalRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PrincipalAuthority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<PrincipalAuthority> authority) {
        this.authority = authority;
    }

    public List<PrincipalRole> getRoles() {
        return roles;
    }

    public void setRoles(List<PrincipalRole> roles) {
        this.roles = roles;
    }
}
