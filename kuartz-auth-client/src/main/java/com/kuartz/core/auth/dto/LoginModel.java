package com.kuartz.core.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginModel {

    @NotBlank
    @Size(min = 3, max = 60) // todo constanta alalim
    private String usernameOrEmail;

    @NotBlank
    @Size(min = 3, max = 60) // todo constanta alalim.
    private String password;

    public LoginModel() {
        //    bos yapici
    }

    public LoginModel(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password        = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
