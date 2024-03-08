package com.sparta.hh99springlv4.lecture.controller;

import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.dto.LectureTeacherResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.service.LectureService;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.sparta.hh99springlv4.user.entity.UserRoleEnum.Authority.ADMIN;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
    @Secured(ADMIN)
    @PostMapping("/lecture")
    public ResponseEntity<?> createLecture(@RequestBody LectureRequestDto lectureRequestDto) {

      LectureResponseDto lectureResponseDto = lectureService.createLecture(lectureRequestDto);

      return ResponseEntity.ok(lectureResponseDto);

    }

    // 선택한 강의 조회
    @GetMapping("/select/lecture/{lectureId}")
    public LectureTeacherResponseDto selectLecture(@PathVariable Long lectureId) {

        LectureTeacherResponseDto lectureTeacherResponseDto = lectureService.selectLecture(lectureId);

        return lectureTeacherResponseDto;
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/find/lecture/category/{lectureCategory}")
    public List<LectureResponseDto> findCategoryLecture(
            @PathVariable CategoryEnum lectureCategory,
            @RequestParam(required = false, defaultValue = "false") Boolean name,
            @RequestParam(required = false, defaultValue = "false") Boolean price,
            @RequestParam(required = false, defaultValue = "false") Boolean registrationDate,
            @RequestParam(required = false, defaultValue = "false") Boolean asc,
            @RequestParam(required = false, defaultValue = "false") Boolean desc){

        return lectureService.findCategoryLecture(lectureCategory, name, price, registrationDate, asc, desc);

    }

}
