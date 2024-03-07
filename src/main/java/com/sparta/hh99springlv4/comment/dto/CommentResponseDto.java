package com.sparta.hh99springlv4.comment.dto;

import com.sparta.hh99springlv4.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {

    private String comments; //댓글내용
    private Long commentLikeCounts; //좋아요
    private LocalDate commentCreatedAt; //등록일
    private LocalDate commentModifiedAt; //수정일

    public CommentResponseDto(Comment comment) {
        this.comments = comment.getComments();
        this.commentLikeCounts = comment.getCommentLikeCounts();
        this.commentCreatedAt = comment.getCommentCreatedAt();
        this.commentModifiedAt = comment.getCommentModifiedAt();
    }

//    public CommentResponseDto(Comment comment) {
//        this.contents = comment.getContents();
//        this.likes = comment.isLikes();
//        this.createdAt = comment.getCreatedAt();
//        this.modifiedAt = comment.getModifiedAt(); //수정일 나중에 확인
//    }
}
