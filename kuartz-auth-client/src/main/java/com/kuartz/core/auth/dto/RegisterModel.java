package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
public class RegisterModel extends KuartzModel {

    // todo constraints koyalim.
    private String username;

    // todo constraints koyalim. Password kontrolleri koyalim 8 karakter olmali vs.
    private String password;

    @Email
    private String email;

    private String name;

    private String surname;
}
