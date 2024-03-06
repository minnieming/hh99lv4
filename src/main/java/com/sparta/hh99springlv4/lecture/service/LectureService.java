package com.sparta.hh99springlv4.lecture.service;


import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    // 강의 등록 기능
    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto lectureRequestDto) {

        Teacher teacher = teacherRepository.findByTeacherName(lectureRequestDto.getTeacher());
        if (teacher == null) {
            throw new IllegalArgumentException("해당 선생님을 찾지 못했습니다");
        }

        // RequestDto -> Entity
        Lecture lecture = new Lecture(lectureRequestDto);
        lecture.setTeacher(teacher);

        // DB에 저장
        Lecture saveLecture = lectureRepository.save(lecture);

        // Entity -> ResponseDto
        LectureResponseDto lectureResponseDto = new LectureResponseDto(saveLecture);

        return lectureResponseDto;
    }


    // 선택한 강의 정보 수정
    public LectureResponseDto infoLecture(Long lectureId, LectureRequestDto lectureRequestDto) {

        // 선택한 강의 정보 조회
        Lecture lecture = findLecture(lectureId);

        // 강의 정보 수정
        lecture.setLectureName(lectureRequestDto.getLectureName());
        lecture.setPrice(lectureRequestDto.getPrice());
        lecture.setIntroL(lectureRequestDto.getIntroL());
        lecture.setCategory(CategoryEnum.valueOf(lectureRequestDto.getCategory())); // 카테고리 수정


        // 변경된 강의 정보 DB에 저장
        lecture = lectureRepository.save(lecture);

        return new LectureResponseDto(lecture);
    }


    // 강의 조회 메서드
    private Lecture findLecture(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의는 존재하지 않습니다."));
    }


    // 선택한 강의 조회
    public LectureResponseDto updateLecture(Long lectureId) {

        // 선택 강의가 lecture DB에 존재하는지 확인
        Lecture lecture = findLecture(lectureId);

        return new LectureResponseDto(lecture);
    }


    // 카테고리별 강의 목록 조회 기능
    public List<LectureResponseDto> findLecturesByCategory(CategoryEnum category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        List<LectureResponseDto> lectureResponseDtos = new ArrayList<>();
        for (Lecture lecture : lectures) {
            lectureResponseDtos.add(new LectureResponseDto(lecture));
        }
        return lectureResponseDtos;
    }

}

