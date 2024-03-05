package com.sparta.hh99springlv4.user.service;


import com.sparta.springlv3.user.dto.SignupRequestDto;
import com.sparta.springlv3.user.dto.SignupResponseDto;
import com.sparta.springlv3.user.entity.DepartmentEnum;
import com.sparta.springlv3.user.entity.User;
import com.sparta.springlv3.user.entity.UserRoleEnum;
import com.sparta.springlv3.user.repository.UserRepository;
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


        // 사용자 ROLE 확인 (권한확인)
        UserRoleEnum role = UserRoleEnum.STAFF;  // 일반 사용자 권한을 넣어놓은다.
        // 사용자 department 확인
        DepartmentEnum department = DepartmentEnum.valueOf(requestDto.getDepartment());
        if (department != DepartmentEnum.MARKETING) {   // boolean type 은 is 로 시작함(규칙), isAdmin // (true)면 관리자 권한으로 회원가입
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.MANAGER;  // 위에서 USER -> ADMIN 권한으로 덮어짐.
        }

        // 사용자 등록
        User user = new User(email, password, department, role);  // 등록하려면 user entity 클래스 객체를 만듦 : JPA 에서 Entity class 객체 하나가 DB의 한 열과 같다. (안의 내용은 생성자) 생성자를 통해서 만듦. 빨간 밑줄 뜨면 Create Constructor ^^
        userRepository.save(user);
        return new SignupResponseDto(user);
    }


}
