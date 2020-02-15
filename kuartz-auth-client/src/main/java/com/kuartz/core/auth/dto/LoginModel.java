package com.kuartz.core.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {

    @NotBlank
    @Size(min = 3, max = 60) // todo constanta alalim
    private String usernameOrEmail;

    @NotBlank
    @Size(min = 3, max = 60) // todo constanta alalim.
    private String password;

}
