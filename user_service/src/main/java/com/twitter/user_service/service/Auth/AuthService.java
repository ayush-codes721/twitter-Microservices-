package com.twitter.user_service.service.Auth;

import com.twitter.user_service.dto.LoginRequestDTO;
import com.twitter.user_service.dto.SignupRequestDTO;
import com.twitter.user_service.dto.UserDTO;
import com.twitter.user_service.exceptions.AlreadyExistsException;
import com.twitter.user_service.exceptions.ResourceNotFoundException;
import com.twitter.user_service.model.User;
import com.twitter.user_service.repo.UserRepo;
import com.twitter.user_service.service.JWT.JwtService;
import com.twitter.user_service.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public UserDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userRepo.findByUsername(signupRequestDTO.getUsername()).isPresent()) {
            throw new AlreadyExistsException("user already exist");
        }
        User user = modelMapper.map(signupRequestDTO, User.class);
        user.setPassword(PasswordUtils.hashPassword(signupRequestDTO.getPassword()));
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {


        User user = userRepo.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        boolean isMatch = PasswordUtils.checkPassword(loginRequestDTO.getPassword(), user.getPassword());
        if (!isMatch) {
            throw new RuntimeException("password is wrong");
        }

        String token = jwtService.createAccessToken(user);


        return token;
    }
}
