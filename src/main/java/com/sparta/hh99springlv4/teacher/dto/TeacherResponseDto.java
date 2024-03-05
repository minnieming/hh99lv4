package com.sparta.hh99springlv4.teacher.dto;

import com.sparta.springlv3.lecture.entity.Lecture;
import com.sparta.springlv3.teacher.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String teacher_name; //강사이름
    private int career; //경력
    private String company; //회사
    private String phone; //전화번호
    private String introduction; //소개

    public TeacherResponseDto(Teacher teacher) {
        this.teacher_name = teacher.getTeacher_name();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phone = teacher.getPhone();
        this.introduction = teacher.getIntroduction();
    }

    public TeacherResponseDto (Lecture lecture) {
        this.teacher_name = lecture.getTeacher().getTeacher_name();
        this.career = lecture.getTeacher().getCareer();
        this.company = lecture.getTeacher().getCompany();
        this.phone = lecture.getTeacher().getPhone();
        this.introduction = lecture.getTeacher().getIntroduction();
    }
}


