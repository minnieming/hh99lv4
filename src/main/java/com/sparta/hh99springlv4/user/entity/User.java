package com.sparta.hh99springlv4.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum 값과 매핑
    private DepartmentEnum department;

//    @Column(nullable = false)
//    private String auth;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String password, DepartmentEnum selectedDepartmentString, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.department = selectedDepartmentString;
        this.role = role;
    }


}


