package com.board.board.dto;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Setter
@Getter



public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]*${4,10}")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]*${8,15}")
    private String password;

    //    private String adminToken = "";
}
