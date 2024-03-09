package com.sparta.hh99springlv4.likes.repository;

import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Likes findByUserAndLecture(User user, Lecture lecture);

    Likes findByLecture(Lecture lecture);

    long countByLecture(Lecture lecture);

}

