package com.sparta.blogpost.service;


import com.sparta.blogpost.dto.LoginRequestDto;
import com.sparta.blogpost.dto.MessageResponseDto;
import com.sparta.blogpost.dto.SignupRequestDto;
import com.sparta.blogpost.entity.User;
import com.sparta.blogpost.enums.UserRoleEnum;
import com.sparta.blogpost.jwt.JwtUtil;
import com.sparta.blogpost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "DeXi341@dNDI";

    @Transactional
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
//        String email = signupRequestDto.getEmail();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return new MessageResponseDto("중복된 사용자가 존재합니다.", 400);
        }        // 회원 중복 확인
//        Optional<User> emailFound = userRepository.findByEmail(email);
//        if (emailFound .isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
//        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                return new MessageResponseDto("관리자 암호가 틀려 등록이 불가능합니다.", 400);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        return new MessageResponseDto("회원 가입 완료", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public MessageResponseDto login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        // 사용자 확인
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return new MessageResponseDto("사용자가 존재하지 않습니다.", 400);
        }
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.get().getPassword())){
            return new MessageResponseDto("비밀번호가 틀렸습니다.", 400);
        }
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));
        return new MessageResponseDto(jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));
    }
}