package com.sparta.hh99springlv4.user.dto;

import com.sparta.springlv3.user.entity.DepartmentEnum;
import com.sparta.springlv3.user.entity.User;
import com.sparta.springlv3.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private DepartmentEnum department;

    @NotBlank
    private UserRoleEnum role;

//    @NotBlank
//    private String createUserId;
//
//    private boolean admin = false;
    private String adminToken = "";

    public SignupResponseDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.department = user.getDepartment();
        this.role = user.getRole();
    }
}
