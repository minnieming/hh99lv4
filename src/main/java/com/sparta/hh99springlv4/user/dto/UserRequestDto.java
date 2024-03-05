package com.sparta.hh99springlv4.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String email; //이메일
    private String password; //비밀번호
    private String department; //부서
    private String auth;  //권한
}
