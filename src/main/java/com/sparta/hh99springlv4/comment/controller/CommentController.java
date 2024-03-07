package com.sparta.hh99springlv4.comment.controller;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.service.CommentService;
import com.sparta.hh99springlv4.user.entity.User;
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

//    // 선택한 강의의 댓글 수정
//    @PutMapping("/lectures/{lectureId}/comments/{commentId}")
//    // 코멘트 아이디만 있어도 댓글 수정이 가능하니, 렉쳐 아이디는 불필요할 수 있다. 매개변수가 많은 건 권장하지 않음
//    // 아예 리퀘스트디티오에 담아서 한꺼번에 담는 방법도 있다.
//    public CommentResponseDto updateComment(@PathVariable Long lectureId, Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return commentService.updateComment(lectureId, commentId, commentRequestDto, userDetails);
//    }
//
//    // 선택한 강의의 선택한 댓글 삭제
//    @DeleteMapping("/lectures/{lectureId}/comments/{commentId}")
//    public CommentResponseDto deleteComment(@PathVariable Long lectureId, Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return commentService.deleteComment(lectureId, commentId);
//    }
//
//    // 선택한 강의 좋아요 등록
////    @PostMapping("/{lectureId}/likes")
////    public CommentResponseDto likesLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
////        return commentService.likesLecture(lectureId);
////    }
}
