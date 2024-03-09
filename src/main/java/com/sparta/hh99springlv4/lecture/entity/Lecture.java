package com.sparta.hh99springlv4.lecture.entity;


import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
//    @JsonIgnore
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likeLists = new ArrayList<>();

    // 좋아요 추가
    public void addLike(User user) {
        Likes like = new Likes(this, user);
        likeLists.add(like);
        likeCounts++;
    }

    // 좋아요 취소
    public void removeLike(User user) {
        for (Likes like : likeLists) {
            if (like.getLecture().equals(this) && like.getUser().equals(user) && this.likeCounts > 0) {
                likeLists.remove(like);
                likeCounts--;
                return; // 좋아요를 찾았으므로 반복 종료
            }
        }
    }

//    // 사용자의 좋아요 여부 확인
//    public boolean isLikedByUser(User user) {
//        for (Likes like : likesList) {
//            if (like.getUser().equals(user)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public Lecture(LectureRequestDto lectureRequestDto) {
        this.lectureName = lectureRequestDto.getLectureName();
        this.lecturePrice = lectureRequestDto.getLecturePrice();
        this.lectureIntro = lectureRequestDto.getLectureIntro();
        this.lectureCategory = CategoryEnum.valueOf(String.valueOf(lectureRequestDto.getLectureCategory()));
        this.lectureRegistrationDate = LocalDate.now();
//        this.lectureRegistrationDate = lectureRequestDto.getLectureRegistrationDate();
    }

//    // 좋아요 카운트 증가
//    public void incrementLikesCount() {
//        this.likesCount++;
//    }
//
//    // 좋아요 카운트 감소
//    public void decrementLikesCount() {
//        if (this.likesCount > 0) {
//            this.likesCount--;
//        }
//    }

}
