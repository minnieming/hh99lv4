package com.sparta.hh99springlv4.comment.service;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.exception.UnauthorizedException;
import com.sparta.hh99springlv4.user.repository.UserRepository;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    // 선택한 강의 댓글 등록
    public Pair<CommentResponseDto, String> createComment(CommentRequestDto createCommentRequestDto) {

        // 작성한 유저 정보 가져오기
        Optional<User> userEmailOptional = userRepository.findByUserEmail(createCommentRequestDto.getUserEmail());
        if (!userEmailOptional.isPresent()) {
            throw new IllegalArgumentException("작성자를 찾지 못했습니다");
        }
        User userEmail = userEmailOptional.get();

        // 선택한 강의 가져오기
        Lecture lectureName = lectureRepository.findByLectureName(createCommentRequestDto.getLectureName());
        if (lectureName == null) {
            throw new IllegalArgumentException("해당 강의를 찾지 못했습니다");
        }

        // 클라이언트가 작성한 댓글을 entity에 넣기
        Comment comment = new Comment(createCommentRequestDto);
        comment.setUser(userEmail);
        comment.setLecture(lectureName);
        comment.setCommentLikeCounts(0);

        // entity에 넣은 댓글을 repository에 넣기
        Comment saveComment = commentRepository.save(comment);

        // 클라이언트에게 알려야 하니까, DB에 저장한 값을 responsedto로 보내주기
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
        // 댓글 성공 확인 값 반환
        String successMessage = "댓글이 성공적으로 작성되었습니다";

        return Pair.of(commentResponseDto, successMessage);
    }

    // 선택한 강의의 선택한 댓글 수정
    public CommentResponseDto updateComment(Long lectureId, Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        // DB에서 commentId로 댓글 정보 조회
        // 이 과정을 통해서 먼저 comment에 정보들을 넣어야 다른 과정들이 진행된다!
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // 조회한 댓글이 선택한 강의의 댓글인지 확인
        if (!comment.getLecture().getId().equals(lectureId)) {
            throw new NotFoundException("해당 강의의 댓글이 아닙니다.");
        }

        // 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 수정하기 -> null 확인
        // 이번 경우에는 entity에 바꾸는 메서드를 만들어서 가져와서 사용했다. (댓글 기능 넣을때 dto에 메서드? 만들어서 사용했던 것 처럼!
        comment.updateComment(commentRequestDto);
        if (commentRequestDto == null) {
            throw new IllegalArgumentException("수정 할 댓글이 존재하지 않습니다.");
        }
        commentRepository.save(comment);
        comment.getCommentModifiedAt(LocalDate.now());

        return new CommentResponseDto(comment);
    }

    // 선택한 강의의 선택한 댓글 삭제
    public String deleteComment(Long lectureId, Long commentId, UserDetailsImpl userDetails) {

        // 댓글 가져와서 entity에 정보 넣고 조회하기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NotFoundException("해당 댓글을 찾을 수 업습니다"));

        // 강의에 맞는 댓글인지, 강의 확인하기!
        if (!comment.getLecture().getId().equals(lectureId)) {
            throw new NotFoundException("해당 강의의 댓글이 아닙니다");
        }

        // 댓글을 삭제해도 되는 본인인지, 본인 여부 확인하기!
        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 삭제 할 수 있습니다.");
        }

        // 댓글 삭제하기
        commentRepository.delete(comment);

        return "삭제가 완료되었습니다";
    }





//    // 댓글 등록 기능  // + entity ? 선택한 강의를 조회할 때 해당 강의에 등록된 댓글들도 함께 조회할 수 있습니다.
//    public CommentResponseDto createComment(Long lectureId, CommentRequestDto commentRequestDto) {
//
//        // DB 에서 lectureId 로 강의 정보 조회
////        Lecture lecture = lectureRepository.findByLectureId(lectureId)
////                .orElseThrow(() -> new NotFoundException("해당 강의는 존재하지 않습니다: " + lectureId));
//
//        // 댓글 생성
//        Comment comment = new Comment(commentRequestDto);
//        Comment saveComment = commentRepository.save(comment);
//        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
//
//        return commentResponseDto;
//    }

//    // 선택한 강의의 댓글 수정  // + lecture 비활성화
//    @Transactional
//    public CommentResponseDto updateComment(Long lectureId, Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
//
//        // 1. 댓글 조회
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new NotFoundException("해당 댓글은 존재하지 않습니다."));
//
//        // 2. 조회한 댓글이 선택한 강의의 댓글인지 확인
//        // 서비스상 해당 댓글이 선택한 강의의 댓글인지 확인하는 건 덜 중요할 수도
//        if (!comment.getLecture().getId().equals(lectureId)) {
//            throw new NotFoundException("해당 강의의 댓글이 아닙니다.");
//        }
//
//        // 3. 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
//        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
//            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
//        }
//
//        // 댓글 수정
//        comment.updateContents(commentRequestDto);
//        comment = commentRepository.save(comment);
//
//        return new CommentResponseDto(comment);
//    }
//
//    // 선택한 강의의 선택한 댓글 삭제 // + 댓글 등록한 회원만 수정 가능
//    public CommentResponseDto deleteComment(Long lectureId, Long commentId) {
//
//        // DB 에서 commentId, lectureId 로 댓글 정보 조회
//        Comment comment = commentRepository.findByLectureIdAndCommentId(lectureId, commentId)
//                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));
//
//        // 댓글 삭제
//        commentRepository.delete(comment);
//
//        return new CommentResponseDto(comment);
//    }
//
//
//    // 선택한 강의 좋아요 등록  // + 과제에 불린값 // 선택 강의 조회 시 좋아요 수 함께 조회
//    public boolean likesLecture(Long userId, Long lectureId) {
////        Lecture lecture = lectureRepository.findByLectureId(lectureId)
////                .orElseThrow(() -> new NotFoundException("해당 강의를 찾을 수 없습니다." + lectureId));
//        return false;
//    }
//
////        Like like = likeRepository.findByUserIdAndLectureId(userId, lectureId);
////
////        if (like == null) {
////            // 좋아요가 없는 경우 -> 좋아요 등록
////            like = new Like();
////            like.setUser(userId);
////            like.setLecture(lecture);
////            likeRepository.save(like);
////            return true; // 좋아요 등록 성공
////        } else {
////            // 좋아요가 있는 경우 -> 좋아요 취소
////            likeRepository.delete(like);
////            return false; // 좋아요 취소 성공
//
//
}
