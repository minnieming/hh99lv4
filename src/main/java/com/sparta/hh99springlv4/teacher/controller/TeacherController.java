package com.sparta.hh99springlv4.teacher.controller;


import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.service.TeacherService;
import com.sparta.hh99springlv4.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    // 강사 등록
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {

        TeacherResponseDto teacherResponseDto = teacherService.createTeacher(teacherRequestDto);

        return ResponseEntity.ok(teacherResponseDto);
    }

    // @Secured("ROLE_ADMIN")
    // @Secured(UserRoleEnum.ADMIN)

}
