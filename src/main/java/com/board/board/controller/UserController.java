package com.board.board.controller;


import com.board.board.dto.LoginRequestDto;
import com.board.board.dto.LoingResponseDto;
import com.board.board.dto.SignupRequestDto;
import com.board.board.dto.SignupResponseDto;
import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);
        return new SignupResponseDto();
    }
    //    @PostMapping("/login")
//    public String login(LoginRequestDto loginRequestDto) {
//        userService.login(loginRequestDto);
//        return "redirect:/api/shop";
//    }
    //JWT
    @PostMapping("/login")
    public  LoingResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return  new  LoingResponseDto();
    }
}
