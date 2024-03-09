package com.sparta.hh99springlv4.likes.entity;

import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 좋아요 여부
    private LocalDateTime likesTime;

    @ManyToOne
    @JoinColumn (name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Likes(Lecture lecture, User user) {
        this.lecture = lecture;
        this.user = user;
        this.likesTime = LocalDateTime.now();
    }

}
