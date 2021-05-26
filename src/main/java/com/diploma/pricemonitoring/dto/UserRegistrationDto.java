package com.diploma.pricemonitoring.dto;

import com.diploma.pricemonitoring.model.Role;

public class UserRegistrationDto {
    private String email;
    private String name;
    private String password;
    private Role role;

    public UserRegistrationDto(String email, String name, String password, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserRegistrationDto() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRegistrationDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserRegistrationDto setRole(Role role) {
        this.role = role;
        return this;
    }


}
