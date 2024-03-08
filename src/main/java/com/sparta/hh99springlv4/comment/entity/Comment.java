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

    @Column(nullable = false) // 좋아요
    private boolean commentLikes;

    @Column(nullable = false) // 좋아요 갯수
    private long commentLikeCounts;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    ------------------------- SELF 추가 구현 -------------------------

    @Column(nullable = false) //등록일
    private LocalDate commentCreatedAt;

    @Column(nullable = false) //수정일
    private LocalDate CommentModifiedAt;

//    --------------------------- 메서드 -------------------------------

    public Comment(CommentRequestDto createCommentRequestDto) {
        this.comments = createCommentRequestDto.getComments();
        this.commentCreatedAt = LocalDate.now();
        this.CommentModifiedAt = LocalDate.now();
    }

    // 댓글 수정 메서드
    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comments = commentRequestDto.getComments();
    }

//    // Comment 엔티티(주인)에서 Lecture 엔티티에 대해 참조해야함.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lecture_id")
//    private Lecture lecture;
//
//    // 여러개의 댓글이 하나의 사용자에게만 속함.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    // Lecture 엔티티에 대한 참조를 반환하는 메서드
//    public Lecture getLecture() {
//        return this.lecture;
//    }
//
//    // 댓글을 작성한 사용자 정보를 반환하는 메서드
//    public User getUser() {
//        return this.user;
//    }
//
//    public Comment(CommentRequestDto commentRequestDto) {
//        this.contents = commentRequestDto.getContents();
//        this.likes = commentRequestDto.isLikes();
////        this.createdAt = LocalDate.now();
//    }
//
//
}
