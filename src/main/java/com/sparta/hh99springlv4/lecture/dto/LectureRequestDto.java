package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.springlv3.teacher.entity.Teacher;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureRequestDto {
    private String lectureName; //강의명
    private Long price; //가격
    private String introL; //소개
    private String category; //카테고리
    private LocalDate registrationDate; //등록일
    private Teacher teacher; //강사이름


}
