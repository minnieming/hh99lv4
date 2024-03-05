package com.sparta.hh99springlv4.user.service;

import com.sparta.hh99springlv4.user.dto.SignupRequestDto;
import com.sparta.hh99springlv4.user.dto.SignupResponseDto;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원 가입
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();  // requestDto 에서 getUsername 가져와 변수 username 에 담음.
        String password = passwordEncoder.encode(requestDto.getPassword());  // 평문을 암호화 해서 password 에 담음.


        // email 중복확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }


//        // 사용자 ROLE 확인 (권한확인)
//        UserRoleEnum role = UserRoleEnum.USER;
//
//        DepartmentEnum department = DepartmentEnum.valueOf(requestDto.getDepartment());
//        if (department != DepartmentEnum.MARKETING) {
//            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        // 사용자 등록
        User user = new User(email, password, role);
        userRepository.save(user);
        return new SignupResponseDto(user);
    }


}
