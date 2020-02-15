package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;

import javax.validation.constraints.Email;

public class RegisterModel extends KuartzModel {

    // todo constraints koyalim.
    private String username;

    // todo constraints koyalim. Password kontrolleri koyalim 8 karakter olmali vs.
    private String password;

    @Email
    private String email;

    private String name;

    private String surname;

    public RegisterModel() {
        //     bos yapici
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
