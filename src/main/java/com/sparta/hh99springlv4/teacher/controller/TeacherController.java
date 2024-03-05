package com.sparta.hh99springlv4.teacher.controller;


import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.service.TeacherService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/teacher")
    // 로그인을 통해 발급받은 JWT 함께 요청
    // @AuthenticationPrincipal UserDetails userDetails 매개변수를 사용하여 현재 요청에 대한 사용자 정보를 가져옵니다. 이 정보는 Spring Security를 사용하여 로그인 후에 JWT 인증 토큰을 통해 얻어진 사용자 정보
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto teacherRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자가 관리자(매니저, 스태프)인지 확인
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return teacherService.createTeacher(teacherRequestDto);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 강사를 등록할 수 없습니다.");
    }

    // 선택한 강사 정보 수정
    @PutMapping("/teacherInfo/{teacherId}")
    public TeacherResponseDto infoTeacher(@PathVariable Long teacherId, @RequestBody TeacherRequestDto teacherRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return teacherService.infoTeacher(teacherId, teacherRequestDto);
        }
        throw new IllegalArgumentException("매니저가 아닙니다. 선택한 정보를 수정 할 수 없습니다.");
    }

    // 선택한 강사 조회
    @GetMapping("/teacher/{teacherId}")
    public TeacherResponseDto updateTeacher(@PathVariable Long teacherId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return teacherService.updateTeacher(teacherId);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사를 조회 할 수 없습니다.");
    }

    // 선택한 강사가 촬영한 강의 목록 조회
    @GetMapping("/lecture/{teacherId}")
    public List<LectureResponseDto> findTeacherLecutre(@PathVariable Long teacherId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return teacherService.findTeacherLecture(teacherId);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사가 촬영한 강의 목록 조회를 할 수 없습니다.");
    }

}
