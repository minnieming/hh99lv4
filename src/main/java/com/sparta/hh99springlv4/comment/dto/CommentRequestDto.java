package com.sparta.hh99springlv4.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String userEmail; // 이용자 확인
    private String lectureName; // 강의
    private String comments; //댓글내용
    private long commentLikeCounts; // 좋아요 개수
}
