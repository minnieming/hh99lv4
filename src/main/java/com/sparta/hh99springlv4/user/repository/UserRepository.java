package com.sparta.hh99springlv4.user.repository;

import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
    Optional<User> findByUserEmail(String userEmail);

//    Optional<User> findByUsername(String userDetails);
}


