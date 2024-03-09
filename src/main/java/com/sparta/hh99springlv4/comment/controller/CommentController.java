package com.sparta.hh99springlv4.comment.controller;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.service.CommentService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 선택한 강의의 댓글 등록
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/lecture/comments")
    public Pair<CommentResponseDto, String> createComment(@RequestBody CommentRequestDto createCommentRequestDto) {

        return commentService.createComment(createCommentRequestDto);

    }

    // 선택한 강의의 댓글 수정
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/lectures/{lectureId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long lectureId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(lectureId, commentId, commentRequestDto, userDetails);
    }


    // 선택한 강의의 선택한 댓글 삭제
    @DeleteMapping("/lectures/{lectureId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long lectureId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(lectureId, commentId, userDetails);
    }
}
