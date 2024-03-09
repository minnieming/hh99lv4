package com.sparta.hh99springlv4.teacher.service;


import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;


    // 강사 등록 기능
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {

        // RequestDto -> Entity
        Teacher teacher = new Teacher(teacherRequestDto);

        // DB에 저장
        Teacher saveTeacher = teacherRepository.save(teacher);

        // Entity -> ResponseDto
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto(saveTeacher);

        return teacherResponseDto;
    }

}
