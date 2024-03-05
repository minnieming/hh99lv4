package com.sparta.hh99springlv4.user.controller;


import com.sparta.hh99springlv4.user.dto.SignupRequestDto;
import com.sparta.hh99springlv4.user.dto.SignupResponseDto;
import com.sparta.hh99springlv4.user.dto.UserInfoDto;
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
}