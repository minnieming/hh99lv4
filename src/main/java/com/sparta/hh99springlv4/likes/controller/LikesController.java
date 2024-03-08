package com.sparta.hh99springlv4.likes.controller;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.likes.dto.LikesRequestDto;
import com.sparta.hh99springlv4.likes.service.LikesService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {

    private final LikesService likesService;

    // 선택한 강의 좋아요 등록
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/{lectureId}/likes")
    public String likesLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.likesLecture(lectureId, userDetails);
    }
}
