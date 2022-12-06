package com.board.board.dto;


import lombok.Getter;

@Getter
public class BoardRequestDto { //BoardRequestDto라는 객체 생성
        private String username;
        private String contents;
        private String title;
        private String password;
    }
