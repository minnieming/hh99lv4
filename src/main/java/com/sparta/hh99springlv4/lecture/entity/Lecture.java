package com.sparta.hh99springlv4.lecture.entity;


import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private Long lecturePrice;

    @Column(nullable = false)
    private String lectureIntro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum 값과 매핑
    private CategoryEnum lectureCategory;

    @Column(nullable = false)
    private LocalDate lectureRegistrationDate; // 타입 임시 지정

    @Column(nullable = false)
    private Long likeCounts; // 좋아요 카운트

//    ------------------------- 연관 관계 매핑 -------------------------

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", orphanRemoval = true)
    private List<Likes> likeLists = new ArrayList<>();

//    --------------------------- 메서드 -------------------------------

    public Lecture(LectureRequestDto lectureRequestDto) {
        this.lectureName = lectureRequestDto.getLectureName();
        this.lecturePrice = lectureRequestDto.getLecturePrice();
        this.lectureIntro = lectureRequestDto.getLectureIntro();
        this.lectureCategory = CategoryEnum.valueOf(String.valueOf(lectureRequestDto.getLectureCategory()));
        this.lectureRegistrationDate = LocalDate.now();
    }


}
