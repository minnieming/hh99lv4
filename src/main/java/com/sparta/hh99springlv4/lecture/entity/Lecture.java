package com.sparta.hh99springlv4.lecture.entity;


import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
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
    private Long price;

    @Column(nullable = false)
    private String introL;


    @Column(nullable = false)
    private LocalDate registrationDate; // 타입 임시 지정

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum 값과 매핑
    private CategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

//    // 임의로 매팽
//    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();


    public Lecture(LectureRequestDto lectureRequestDto) {
        this.lectureName = lectureRequestDto.getLectureName();
        this.price = lectureRequestDto.getPrice();
        this.introL = lectureRequestDto.getIntroL();
        this.category = CategoryEnum.valueOf(lectureRequestDto.getCategory());
        this.registrationDate = LocalDate.now();

    }

}
