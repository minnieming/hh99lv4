package com.sparta.hh99springlv4.comment.service;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.exception.UnauthorizedException;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final LectureRepository lectureRepository;
    private final CommentRepository commentRepository;


    // 댓글 등록 기능  // + entity ? 선택한 강의를 조회할 때 해당 강의에 등록된 댓글들도 함께 조회할 수 있습니다.
    public CommentResponseDto createComment(Long lectureId, CommentRequestDto commentRequestDto) {

        // DB 에서 lectureId 로 강의 정보 조회
        Lecture lecture = lectureRepository.findByLectureId(lectureId)
                .orElseThrow(() -> new NotFoundException("해당 강의는 존재하지 않습니다: " + lectureId));

        // 댓글 생성
        Comment comment = new Comment(commentRequestDto);
        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    // 선택한 강의의 댓글 수정  // + lecture 비활성화
    @Transactional
    public CommentResponseDto updateComment(Long lectureId, Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        // 1. 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글은 존재하지 않습니다."));

        // 2. 조회한 댓글이 선택한 강의의 댓글인지 확인
        if (!comment.getLecture().getId().equals(lectureId)) {
            throw new NotFoundException("해당 강의의 댓글이 아닙니다.");
        }

        // 3. 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userDetails.getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 수정
        comment.updateContents(commentRequestDto);
        comment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 선택한 강의의 선택한 댓글 삭제 // + 댓글 등록한 회원만 수정 가능
    public CommentResponseDto deleteComment(Long lectureId, Long commentId) {

        // DB 에서 commentId, lectureId 로 댓글 정보 조회
        Comment comment = commentRepository.findByLectureIdAndCommentId(lectureId, commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // 댓글 삭제
        commentRepository.delete(comment);

        return new CommentResponseDto(comment);
    }


    // 선택한 강의 좋아요 등록  // + 과제에 불린값 // 선택 강의 조회 시 좋아요 수 함께 조회
    public CommentResponseDto likesLecture(Long lectureId) {

        // DB 에서 lectureId 로 강의 정보 조회
        Lecture lecture = lectureRepository.findByLectureId(lectureId)
                .orElseThrow(() -> new NotFoundException("해당 강의는 존재하지 않습니다: " + lectureId));

        // 좋아요 등록 및 취소 처리
        // 사용자가 해당 강의를 이미 좋아요 했다면 취소하고, 아니라면 좋아요 등록
        if (lecture.getLikedUsers().contains(currentUser)) {
            lecture.getLikedUsers().remove(currentUser);
        } else {
            lecture.getLikedUsers().add(currentUser);
        }

        lectureRepository.save(lecture);

        // 성공적인 응답 반환
        return new CommentResponseDto("좋아요 등록/취소가 성공적으로 처리되었습니다.");

    }
}
