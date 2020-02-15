package com.kuartz.core.auth.dto.principal;

import lombok.Data;

import java.util.List;

@Data
public class KuartzPrincipalModel {

    private String username;

    private String email;

    private List<KuartzAuthority> authority;

    private List<KuartzRole> roles;

}
