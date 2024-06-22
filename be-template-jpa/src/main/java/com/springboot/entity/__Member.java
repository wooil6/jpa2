//package com.springboot.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//@Getter
//@Setter
//@NoArgsConstructor // 기본 생성자
////@Entity // jpa는 테이블과 1대1로 연결되는 객체는 꼭 붙여야함
//// entity에는 생성자가 없으면 오류가 있어서 @NoArgsConstructor을 꼭 사용하기
//public class Member {
//    @Id
//    @GeneratedValue // 값을 생성하는 것
//    // id는 자동으로 만들어져야하는데 어노테이션을 사용해서 기본으로 생성되도록.
//    private Long memberId;
//    private String email;
//
//    public Member(String email) {
//        this.email = email;
//    }
//}
