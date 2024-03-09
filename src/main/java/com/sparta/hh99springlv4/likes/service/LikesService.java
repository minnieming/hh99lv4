package com.sparta.hh99springlv4.likes.service;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.repository.UserRepository;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private JacksonInject.Value like;

    @Transactional
    // 선택한 강의 좋아요 기능
    public String likesLecture(Long lectureId, UserDetailsImpl userDetails) {

        // 좋아요를 할 해당 강의 정보 entity에 불러오기
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new NotFoundException("해당 강의를 찾을 수 없습니다"));

        User user = userRepository.findByUserEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다"));


        Likes checkLikes = likesRepository.findByUserAndLecture(user, lecture);
//        Likes checkLikes = likesRepository.findByLecture(lecture);
//
        if (checkLikes != null) {
//            likesRepository.findByUserAndLecture(user, lecture);
            likesRepository.delete(checkLikes);
//            lecture.removeLike(user);
//            likesRepository.deleteById((Long) like.getId());
//            lectureRepository.save(lecture);
            return "좋아요가 취소되었습니다";
        }

//        Likes likes = new Likes();
//        likes.setLikesTime(LocalDateTime.now());
//        likes.setLecture(lecture);
//        likes.setUser(user);
//
//        likesRepository.save(likes);
//        lectureRepository.save(lecture);

        Likes likes = new Likes(lecture, user);
        likesRepository.save(likes);
//        lecture.addLike(user);
//        lectureRepository.save(lecture);

        return "좋아요 되었습니다";

//         좋아요를 눌렀는지 안눌렀는지 여부 확인 -> 좋아요를 눌렀으면 취소하기
//        Likes likes = new Likes();
//        if (likes.getLikesTime() != null) {
//            likesRepository.delete(likes.getId());
//        }
//        likes.setLikesTime(LocalDateTime.now());
//        likesRepository.save(likes);
//         return 값으로 좋아요 등록 / 취소 여부 반환하기
    }
}
