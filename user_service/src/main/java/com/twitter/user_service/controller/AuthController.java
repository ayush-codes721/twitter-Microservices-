package com.twitter.user_service.controller;

import com.twitter.user_service.dto.LoginRequestDTO;
import com.twitter.user_service.dto.SignupRequestDTO;
import com.twitter.user_service.dto.UserDTO;
import com.twitter.user_service.service.Auth.AuthService;
import com.twitter.user_service.service.Auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        UserDTO userDTO = authService.signup(signupRequestDTO);
        return ResponseEntity.ok(userDTO);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.login(loginRequestDTO);
        return ResponseEntity.ok(token);
    }
}


