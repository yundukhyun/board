package com.board.board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto { //BoardRequestDto라는 객체 생성
        private String username;
        private String contents;
        private String title;
        private String password;
    }
