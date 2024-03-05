package com.sparta.hh99springlv4.user.dto;

import lombok.Getter;

/// 이거 필요한 클래스인지 확인해주깅 !
@Getter
public class UserRequestDto {
    private String email; //이메일
    private String password; //비밀번호
    private String auth;  //권한
}
