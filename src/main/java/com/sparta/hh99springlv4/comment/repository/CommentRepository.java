package com.sparta.hh99springlv4.comment.repository;

import com.sparta.hh99springlv4.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    Optional<Comment> findByLectureIdAndCommentId(Long lectureId, Long commentId);

    List<Comment> findByLecture_LectureName(String lectureName);

}
