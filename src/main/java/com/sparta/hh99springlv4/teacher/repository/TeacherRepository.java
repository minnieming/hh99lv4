package com.sparta.hh99springlv4.teacher.repository;

import com.sparta.hh99springlv4.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findById(Long id);

    Teacher findByTeacherName(String teacherName);

}
