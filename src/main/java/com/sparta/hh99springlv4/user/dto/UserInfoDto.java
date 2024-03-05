package com.sparta.hh99springlv4.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String email;

    private String password;


    private String auth;

    boolean isAdmin;

    public UserInfoDto(String userEmail, String password) {
        this.email = getEmail();
        this.password = getPassword();
    }
}


