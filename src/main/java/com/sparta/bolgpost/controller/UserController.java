package com.sparta.bolgpost.controller;

import com.sparta.bolgpost.dto.LoginRequestDto;
import com.sparta.bolgpost.dto.MessageResponseDto;
import com.sparta.bolgpost.service.UserService;
import com.sparta.bolgpost.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController//CONTROLLER VS RESTCONTROLLER 두개의 차이점
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //1.회웝가입
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signupPage(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto("회원 가입 완료", HttpStatus.OK.value()));

    }
//    @ResponseBody
//    @PostMapping("/login")
//    public MsgResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        userService.login(loginRequestDto, response);
//
//        return new MsgResponseDto("로그인 성공", HttpStatus.OK.value());
//    }
    
    //2.로그인
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MessageResponseDto("로그인 성공", HttpStatus.OK.value()));
    }
}