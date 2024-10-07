package com.twitter.auth_service.service.User;

import com.twitter.auth_service.exceptions.ResourceNotFoundException;
import com.twitter.auth_service.model.User;
import com.twitter.auth_service.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {
    private final UserRepo userRepo;

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    public Optional<User> getOptionalOfUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepo.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
}
