package com.sparta.hh99springlv4.lecture.repository;

import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByLectureName(String lectureName);

    Optional<Lecture> findById (Long Id);

    // 강의몀
    List<Lecture> findAllByLectureCategoryOrderByLectureNameAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLectureNameDesc(CategoryEnum lectureCategory);

    // 가격
    List<Lecture> findAllByLectureCategoryOrderByLecturePriceAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLecturePriceDesc(CategoryEnum lectureCategory);

    // 등록일
    List<Lecture> findAllByLectureCategoryOrderByLectureRegistrationDateAsc(CategoryEnum lectureCategory);
    List<Lecture> findAllByLectureCategoryOrderByLectureRegistrationDateDesc(CategoryEnum lectureCategory);

}
