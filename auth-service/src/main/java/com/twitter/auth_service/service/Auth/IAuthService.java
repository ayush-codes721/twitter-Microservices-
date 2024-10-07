package com.twitter.auth_service.service.Auth;

import com.twitter.auth_service.DTO.UserDTO;
import com.twitter.auth_service.request.LoginRequest;
import com.twitter.auth_service.request.SignupRequest;
import com.twitter.auth_service.response.LoginResponse;
import jakarta.servlet.http.Cookie;

public interface IAuthService {

    UserDTO signup(SignupRequest signupRequest);

    LoginResponse login(LoginRequest loginRequest);

    Cookie createCookie(String token);

    boolean validateToken(String token);

    Long getUserIdFromToken(String token);

    UserDTO getUserById(Long id);
}
