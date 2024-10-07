package com.twitter.auth_service.controller;

import com.twitter.auth_service.DTO.UserDTO;
import com.twitter.auth_service.request.LoginRequest;
import com.twitter.auth_service.request.SignupRequest;
import com.twitter.auth_service.response.LoginResponse;
import com.twitter.auth_service.service.Auth.IAuthService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;


    @PostMapping("/signup")
    ResponseEntity<UserDTO> signup(@RequestBody SignupRequest signupRequest) {

        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/validateToken")
    ResponseEntity<Boolean> validateToken(@RequestParam String token) {


        return ResponseEntity.ok(authService.validateToken(token));

    }

    @GetMapping("/jwt/getUserId/{token}")
    ResponseEntity<Long> getUserIdFromToken(@PathVariable  String token){

        return ResponseEntity.ok(authService.getUserIdFromToken(token));
    }

    @GetMapping("/user/profile/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

        return ResponseEntity.ok(authService.getUserById(id));
    }

}
