package com.sparta.hh99springlv4.lecture.controller;


import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.service.LectureService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
    @PostMapping("/lecture")
    // 로그인을 통해 발급받은 JWT 함께 요청
    // @AuthenticationPrincipal UserDetails userDetails 매개변수를 사용하여 현재 요청에 대한 사용자 정보를 가져옵니다. 이 정보는 Spring Security를 사용하여 로그인 후에 JWT 인증 토큰을 통해 얻어진 사용자 정보
    public LectureResponseDto createLecture(@RequestBody LectureRequestDto lectureRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자가 관리자(매니저, 스태프)인지 확인
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return lectureService.createLecture(lectureRequestDto);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 강의를 등록할 수 없습니다.");
    }

    // 선택한 강의 정보 수정
    @PutMapping("/lectureinfo/{lectureId}")
    public LectureResponseDto infoLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto lectureRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return lectureService.infoLecture(lectureId, lectureRequestDto);
        }
        throw new IllegalArgumentException("매니저가 아닙니다. 선택한 정보를 수정 할 수 없습니다.");
    }

    // 선택한 강의 조회
    @GetMapping("/select/lecture/{lectureId}")
    public LectureResponseDto updateLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return lectureService.updateLecture(lectureId);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사를 조회 할 수 없습니다.");
    }


    //     카테고리별 강의 목록 조회
    @GetMapping("/find/lecture/{category}")
    public List<LectureResponseDto> findCategoryLecuture(@PathVariable CategoryEnum category, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return lectureService.findLecturesByCategory(category);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사가 촬영한 강의 목록 조회를 할 수 없습니다.");
    }
}
