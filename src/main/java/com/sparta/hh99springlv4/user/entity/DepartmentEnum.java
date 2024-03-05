package com.sparta.hh99springlv4.user.entity;

public enum DepartmentEnum {
    CURRICULUM(Department.CURRICULUM), // 커리큘럼
    MARKETING(Department.MARKETING), // 마케팅
    DEVELOPMENT(Department.DEVELOPMENT); // 개발


    private final String department;

    DepartmentEnum(String department) { // 생성자
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public static class Department { // 메서드
        public static final String CURRICULUM = "ROLE_CURRICULUM";
        public static final String MARKETING = "ROLE_MARKETING";
        public static final String DEVELOPMENT = "ROLE_DEVELOPMENT";
    }
}
