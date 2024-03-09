package com.sparta.hh99springlv4.lecture.service;


import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.dto.LectureTeacherResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    // 강의 등록 기능
    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto lectureRequestDto) {

        Teacher teacherName = teacherRepository.findByTeacherName(lectureRequestDto.getTeacherName());
        if (teacherName == null) {
            throw new IllegalArgumentException("해당 선생님을 찾지 못했습니다");
        }

        // RequestDto -> Entity
        Lecture lecture = new Lecture(lectureRequestDto);
        lecture.setTeacher(teacherName);
        lecture.setLikeCounts(0L);

        // DB에 저장
        Lecture saveLecture = lectureRepository.save(lecture);

        // Entity -> ResponseDto
        LectureResponseDto lectureResponseDto = new LectureResponseDto(saveLecture);

        return lectureResponseDto;
    }

    // 선택한 강의 조회 기능
    public LectureTeacherResponseDto selectLecture(Long lectureId) {

        // 강의 조회
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾지 못했습니다."));

        // 강사 조회
        Teacher teacher = lecture.getTeacher();

        // 댓글 조회
        List<Comment> comments = commentRepository.findByLectureId(lectureId);

        // 좋아요 카운트 조회
        long likeCount = likesRepository.countByLecture(lecture);

        return new LectureTeacherResponseDto(lecture, comments, likeCount);
    }

    // 카테고리별 강의 목록 조회
    public List<LectureResponseDto> findCategoryLecture(CategoryEnum lectureCategory, boolean name, boolean price, boolean registrationDate, boolean asc, boolean desc) {

        // entity에 넣어줄 리스트 타입의 생성자를 호출한다.
        List<Lecture> lectures = null;

        // 강의명, 가격, 등록일을 기준으로 내림차순, 오름차순을 선택
        // -> 하나씩 받아서 쿼리문 + if 문 조합으로 사용
        if (name && asc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureNameAsc(lectureCategory);
        } else if (name && desc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureNameDesc(lectureCategory);
        }

        if (price && asc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLecturePriceAsc(lectureCategory);
        } else if (price && desc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLecturePriceDesc(lectureCategory);
        }

        if (registrationDate && asc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureRegistrationDateAsc(lectureCategory);
        } else if (registrationDate && desc) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureRegistrationDateDesc(lectureCategory);
        }

        if (lectures == null || lectures.isEmpty()) {
            throw new IllegalArgumentException("선택한 카테고리에 해당하는 강의가 없습니다");
        }

        return lectures.stream()
                .map(LectureResponseDto::new)
                .collect(Collectors.toList());

    }


}

