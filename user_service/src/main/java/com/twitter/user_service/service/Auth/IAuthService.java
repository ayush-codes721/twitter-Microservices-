package com.twitter.user_service.service.Auth;

import com.twitter.user_service.dto.LoginRequestDTO;
import com.twitter.user_service.dto.SignupRequestDTO;
import com.twitter.user_service.dto.UserDTO;

public interface IAuthService {

    UserDTO signup(SignupRequestDTO signupRequestDTO);

    String login(LoginRequestDTO loginRequestDTO);

}
