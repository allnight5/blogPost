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
        //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
        MessageResponseDto msg = userService.login(loginRequestDto);
        //문자열 token에 가져온 정보를 넣어주는 부분
        String token = msg.getMessage();
        //헤더를 통해 토큰을 발급해 주는 부분
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        //400은 실패기 때문에 실패한 메시지와 상태코드를 가져온다.
        if(msg.getStatusCode() == 400){
            return new MessageResponseDto(msg.getMessage(), msg.getStatusCode());
        }
        //로그인 성공했으니 로그인을 성공했다는 메시지와 상태 200코드를 보내준다.
        else {
            return new MessageResponseDto("로그인 되었습니다.", 200);
        }
    }
}