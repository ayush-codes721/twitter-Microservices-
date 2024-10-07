package com.twitter.auth_service.service.Auth;

import com.twitter.auth_service.DTO.UserDTO;
import com.twitter.auth_service.exceptions.ResourceNotFoundException;
import com.twitter.auth_service.model.User;
import com.twitter.auth_service.request.LoginRequest;
import com.twitter.auth_service.request.SignupRequest;
import com.twitter.auth_service.response.LoginResponse;
import com.twitter.auth_service.service.JWT.JwtService;
import com.twitter.auth_service.service.User.IUserService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDTO signup(SignupRequest signupRequest) {

        boolean isPresent = userService.getOptionalOfUserByUsername(signupRequest.getUsername()).isPresent();
        if (isPresent) {
            throw new IllegalArgumentException("user already exists");
        }

        User user = modelMapper.map(signupRequest, User.class);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));


        User savedUser = userService.saveUser(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("username and password is required");
        }


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.createAccessToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }

    @Override
    public Cookie createCookie(String token) {

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // Ensures the cookie is only accessible via HTTP and not JavaScript
        cookie.setSecure(true);   // Ensures the cookie is only sent over HTTPS
        cookie.setPath("/");      // Sets the cookie path, which applies to all routes
        cookie.setMaxAge(30 * 60); // Sets the expiration time to 30 minutes (30 * 60 seconds)

        return cookie;
    }

    @Override
    public boolean validateToken(String token) {

        try {
            Long id = jwtService.getIdFromToken(token);

            if (id == null) {
                throw new IllegalArgumentException("Invalid token");
            }

            userService.getUserById(id);

            return true;
        } catch (Exception e) {
            return  false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return jwtService.getIdFromToken(token);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userService.getUserById(id);
        return modelMapper.map(user, UserDTO.class);
    }
}
