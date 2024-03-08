package com.sparta.hh99springlv4.lecture.service;


import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.dto.LectureTeacherResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final CommentRepository commentRepository;

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

        // DB에 저장
        Lecture saveLecture = lectureRepository.save(lecture);

        // Entity -> ResponseDto
        LectureResponseDto lectureResponseDto = new LectureResponseDto(saveLecture);

        return lectureResponseDto;
    }

    // 선택한 강의 조회 기능
    public LectureTeacherResponseDto selectLecture(LectureRequestDto lectureRequestDto) {

        // 강의 조회
        Lecture lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName());
        if(lecture == null) {
            throw new IllegalArgumentException("강의를 찾지 못했습니다");
        }

        // 강사 조회
        Teacher teacher = lecture.getTeacher();

        // 댓글 조회
        List<Comment> comments = commentRepository.findByLecture_LectureName(lectureRequestDto.getLectureName());
        if (comments.isEmpty()) {
            throw new IllegalArgumentException("해당 댓글을 찾지 못했습니다");
        }

//        Lecture selectList = new Lecture(lectureRequestDto);
//        lecture.setCommentList(comment);

        return new LectureTeacherResponseDto(lecture, comments);

//        -------------------------- LectureResponseDto에 같이 넣어서 가져오는 방법 (두개를 따로 반환도 되나?) ------------------------------
//        // 받아온 강의 이름으로 repository에서 다른 정보 찾아오기
//        // lectureRequestDto -> Entity
//        Lecture lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName());
//
//        // Entity -> Repository 저장 (등록 할때만?)
////        Lecture lectureInfo = lectureRepository.save(lecture);
//
//        // 받아온 정보를 반환값으로 주기 & 선생님 정보도 같이 주기 (전화번호는 제외)
//        // Entity -> Response
////        LectureResponseDto lectureResponseDto = new LectureResponseDto(lectureInfo);
//
//        // lectureRequestDto로 들어왔으니까 여기서 가져온다
//        Teacher teacher = lecture.getTeacher();
//
//        TeacherResponseDto teacherResponseDto = new TeacherResponseDto(
//                teacher.getTeacherName(),
//                teacher.getTeacherCareer(),
//                teacher.getTeacherCompany(),
//                teacher.getTeacherIntro()
//        );
//
////        Lecture lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName());
//
//        LectureResponseDto lectureResponseDto = new LectureResponseDto(lecture);
//
//        return new LectureResponseDto(lecture).setTeacher(teacherResponseDto);
//
////        return lectureResponseDto;

    }

    // 카테고리별 강의 목록 조회
    public List<LectureResponseDto> findCategoryLecture(LectureRequestDto lectureRequestDto) {

        // entity에 넣어줄 리스트 타입의 생성자를 호출한다.
        List<Lecture> lectures = null;

        // 강의명, 가격, 등록일을 기준으로 내림차순, 오름차순을 선택
        // -> 하나씩 받아서 쿼리문 + if 문 조합으로 사용
        if (lectureRequestDto.isName() && lectureRequestDto.isAsc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureNameAsc(lectureRequestDto.getLectureCategory());
        } else if (lectureRequestDto.isName() && lectureRequestDto.isDesc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureNameDesc(lectureRequestDto.getLectureCategory());
        }

        if (lectureRequestDto.isPrice() && lectureRequestDto.isAsc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLecturePriceAsc(lectureRequestDto.getLectureCategory());
        } else if (lectureRequestDto.isPrice() && lectureRequestDto.isDesc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLecturePriceDesc(lectureRequestDto.getLectureCategory());
        }

        if (lectureRequestDto.isRegistrationDate() && lectureRequestDto.isAsc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureRegistrationDateAsc(lectureRequestDto.getLectureCategory());
        } else if (lectureRequestDto.isRegistrationDate() && lectureRequestDto.isDesc()) {
            lectures = lectureRepository.findAllByLectureCategoryOrderByLectureRegistrationDateDesc(lectureRequestDto.getLectureCategory());
        }

        if (lectures == null || lectures.isEmpty()) {
            throw new IllegalArgumentException("선택한 카테고리에 해당하는 강의가 없습니다");
        }

        return lectures.stream()
                .map(LectureResponseDto::new)
                .collect(Collectors.toList());

    } // 기준과 정렬을 Enum 타입으로 만들어서 쿼리문을 사용해서 만들려고 함 -> DB에 열이 하나 더 생기는게 싫어서 패쓰


}

