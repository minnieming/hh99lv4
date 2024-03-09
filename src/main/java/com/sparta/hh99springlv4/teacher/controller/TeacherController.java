package com.sparta.hh99springlv4.teacher.controller;


import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.service.TeacherService;
import com.sparta.hh99springlv4.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
