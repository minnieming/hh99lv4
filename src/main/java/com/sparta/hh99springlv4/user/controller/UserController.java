package com.sparta.hh99springlv4.user.controller;

import com.sparta.hh99springlv4.user.dto.*;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.exception.UnauthorizedException;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import com.sparta.hh99springlv4.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 로그인 페이지
    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    // 회원 가입
    @PostMapping("/user/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.signup(requestDto));
    }

    // 로그인
//    @PostMapping("/user/login")
//    public ResponseEntity<UserResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
//        try {
//            // 사용자 인증
//            UserDetails userDetails = userService.
//        }
//    }




//        return handleRequest(() -> {
//            userService.signup(requestDto);
//            return ResponseEntity.ok("성공적으로 회원가입이 완료되었습니다.");
//        });



//    회원가입처리
//    @PostMapping("/user/signup")
//    public ResponseEntity<SignupResponseDto> signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
//        // Validation 예외처리
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        if(fieldErrors.size() > 0) {
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
//            }
//
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(userService.signup(requestDto));
//
////        userService.signup(requestDto);
////        return "성공적으로 회원가입이 되셨습니다.";
//    }


    private ResponseEntity<?> handleRequest(Supplier<ResponseEntity<?>> supplier) {
        try {
            return supplier.get();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인터넷 서버 오류: " + e.getMessage());
        }
    }


    // 회원 관련 정보 받기
    @GetMapping("/user-info") // 사용자 정보 조회
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userEmail = userDetails.getUser().getEmail();
        String password = userDetails.getUser().getPassword();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.MANAGER);

        return new UserInfoDto(userEmail,password);
//        return new UserInfoDto(userEmail, isAdmin);
    }
}


