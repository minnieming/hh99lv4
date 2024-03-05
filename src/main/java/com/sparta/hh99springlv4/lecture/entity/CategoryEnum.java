package com.sparta.hh99springlv4.lecture.entity;

public enum CategoryEnum {

    SPRING (Category.SPRING), // 스프링
    REACT (Category.REACT), // 리액트
    NODE (Category.NODE); // 노드



    private final String category;

    CategoryEnum (String category) {

        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public static class Category {
        public static final String SPRING = "ROLE_SPRING";
        public static final String REACT = "ROLE_REACT";
        public static final String NODE = "ROLE_NODE";
    }


}
