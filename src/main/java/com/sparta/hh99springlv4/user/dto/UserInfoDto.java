package com.sparta.hh99springlv4.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


/// 이거 필요한 클래스인지 확인해주깅 !
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


