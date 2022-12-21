package com.sparta.bolgpost.controller;

import com.sparta.bolgpost.dto.LoginRequestDto;
import com.sparta.bolgpost.dto.MessageResponseDto;
import com.sparta.bolgpost.jwt.JwtUtil;
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

    private final JwtUtil jwtUtil;
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
    public MessageResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        MessageResponseDto msg = userService.login(loginRequestDto);
        String token = msg.getMessage();
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        if(msg.getStatusCode() == 400){
            return new MessageResponseDto(msg.getMessage(), msg.getStatusCode());
        }
        else {
            return new MessageResponseDto("로그인 되었습니다.", 200);
        }
    }
}