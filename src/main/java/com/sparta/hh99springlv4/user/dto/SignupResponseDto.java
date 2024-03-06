package com.sparta.hh99springlv4.user.dto;


import com.sparta.hh99springlv4.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sparta.hh99springlv4.user.entity.User;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {

    private String userEmail; //이메일
    private String userPassword; // 비밀번호
    private String userGender; // 성별
    private String userPhone; //전화번호
    private String userAddress; // 주소
    private UserRoleEnum role;  //권한

    public SignupResponseDto(User user) {
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userGender = user.getUserGender();
        this.userPhone = user.getUserPhone();
        this.userAddress = user.getUserAddress();
        this.role = user.getRole();
    }

//    @Email
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String password;
//
//    @NotBlank
//    private DepartmentEnum department;
//
//    @NotBlank
//    private UserRoleEnum role;
//
//    //    @NotBlank
////    private String createUserId;
////
////    private boolean admin = false;
//    private String adminToken = "";
//
//    public SignupResponseDto(User user) {
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//        this.role = user.getRole();
//    }
}
