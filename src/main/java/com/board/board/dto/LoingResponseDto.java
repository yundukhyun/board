package com.board.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoingResponseDto {

    private String msg = "로그인 성공";
    private int statusCode = 200;
}
