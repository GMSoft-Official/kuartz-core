package com.kuartz.core.common.security;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserPrincipal {

    private String username;

    private String email;

    private Set<PrincipalAuthority> authority;

    private List<PrincipalRole> roles;

}
