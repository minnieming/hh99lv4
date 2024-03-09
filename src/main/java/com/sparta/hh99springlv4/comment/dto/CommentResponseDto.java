package com.sparta.hh99springlv4.comment.dto;

import com.sparta.hh99springlv4.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {

    private String comments; //댓글내용
    private LocalDate commentCreatedAt; //등록일
    private LocalDate commentModifiedAt; //수정일

    public CommentResponseDto(Comment comment) {
        this.comments = comment.getComments();
        this.commentCreatedAt = comment.getCommentCreatedAt();
        this.commentModifiedAt = comment.getCommentModifiedAt();
    }
}
