package com.sparta.bolgpost.controller;

import com.sparta.bolgpost.dto.LoginRequestDto;
import com.sparta.bolgpost.dto.MsgResponseDto;
import com.sparta.bolgpost.service.UserService;
import com.sparta.bolgpost.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/login")
    public ModelAndView loginpage() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }


    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signupPage(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);

        return ResponseEntity.ok(new MsgResponseDto("회원 가입 완료", HttpStatus.OK.value()));

    }
//    @ResponseBody
//    @PostMapping("/login")
//    public MsgResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        userService.login(loginRequestDto, response);
//
//        return new MsgResponseDto("로그인 성공", HttpStatus.OK.value());
//    }
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);

        return ResponseEntity.ok(new MsgResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

}
