package com.sparta.hh99springlv4.comment.entity;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 댓글내용
    private String comments;

//    ------------------------- SELF 추가 구현 -------------------------

    @Column(nullable = false) //등록일
    private LocalDate commentCreatedAt;

    @Column(nullable = false) //수정일
    private LocalDate CommentModifiedAt;

//    ------------------------- 연관 관계 매핑 -------------------------
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    --------------------------- 메서드 -------------------------------

    public Comment(CommentRequestDto createCommentRequestDto) {
        this.comments = createCommentRequestDto.getComments();
        this.commentCreatedAt = LocalDate.now();
        this.CommentModifiedAt = LocalDate.now();
    }

    // 댓글 수정 메서드
    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comments = commentRequestDto.getComments();
        this.CommentModifiedAt = LocalDate.now();
    }

}
