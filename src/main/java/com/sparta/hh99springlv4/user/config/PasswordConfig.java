package com.sparta.hh99springlv4.user.config;//package com.sparta.springlv3.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration // 해당 Bean을 등록하는 메서드가 속해있는 Class 위에 @Configuration 설정
//public class PasswordConfig {
//
//    @Bean // 해당 메서드 위에 Bean 애너테이션 추가
//    public PasswordEncoder passwordEncoder() { // Bean으로 등록하고자 하는 메서드 선언
//        return new BCryptPasswordEncoder();
//    }
//}