//package com.sparta.hh99springlv4.comment.entity;
//
//import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
//import com.sparta.hh99springlv4.lecture.entity.Lecture;
//import com.sparta.hh99springlv4.user.entity.User;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Entity
//@Getter
////@Setter
//@NoArgsConstructor
//@Table(name = "Comment")
//public class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false) //댓글내용
//    private String contents;
//
//    @Column(nullable = false) //좋아요
//    private boolean likes;
//
//    @Column(nullable = false) //등록일
//    private LocalDate createdAt;
//
//    @Column(nullable = false) //수정일
//    private LocalDate modifiedAt;
//
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
//    // 댓글 수정 메서드
//    public void updateContents(CommentRequestDto commentRequestDto) {
//        this.contents = commentRequestDto.getContents();
//    }
//}
