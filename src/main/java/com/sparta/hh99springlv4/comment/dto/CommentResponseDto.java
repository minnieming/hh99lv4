package com.sparta.hh99springlv4.comment.dto;

import com.sparta.hh99springlv4.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {

    private String contents; //댓글내용
    private boolean likes; //좋아요
    private LocalDate createdAt; //등록일
    private LocalDate modifiedAt; //수정일

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.likes = comment.isLikes();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt(); //수정일 나중에 확인
    }
}
