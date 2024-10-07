package com.twitter.auth_service.service.User;


import com.twitter.auth_service.DTO.UserDTO;
import com.twitter.auth_service.model.User;

import java.util.Optional;

public interface IUserService {

    User getUserById(Long userId);
    Optional<User> getOptionalOfUserByUsername(String username);
    User saveUser(User user);


}
