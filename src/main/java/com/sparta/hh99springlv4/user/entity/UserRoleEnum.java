package com.sparta.hh99springlv4.user.entity;

public enum UserRoleEnum {
    MANAGER(Authority.MANAGER),  // 매니저 권한
    STAFF(Authority.STAFF);  // 스태프 권한

    private final String authority;

    UserRoleEnum(String authority) { // 생성자
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority { // 메서드
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}



