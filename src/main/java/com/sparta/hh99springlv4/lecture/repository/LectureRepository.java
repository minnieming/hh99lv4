package com.sparta.hh99springlv4.lecture.repository;

import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    // teacher에서 사용중.
    // Lecture에서 강사와 일치하는 정보들을 내림차순으로 정렬하는 쿼리문
//    @Query("SELECT l FROM Lecture l WHERE l.teacher.id = :teacherId ORDER BY l.registrationDate DESC")
//    List<Lecture> findLecturesByTeacherIdOrderByRegistrationDateDesc(Long teacherId);

//    List<Lecture> findByCategory(CategoryEnum category);
    Lecture findByLectureName(String lectureName);

    // 강의몀
    List<Lecture> findAllByLectureCategoryOrderByLectureNameAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLectureNameDesc(CategoryEnum lectureCategory);

    // 가격
    List<Lecture> findAllByLectureCategoryOrderByLecturePriceAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLecturePriceDesc(CategoryEnum lectureCategory);

    // 등록일
    List<Lecture> findAllByLectureCategoryOrderByLectureRegistrationDateAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLectureRegistrationDateDesc(CategoryEnum lectureCategory);

//    Optional<Lecture> findByLectureId(Long lectureId);
}
