package com.twitter.user_service.service.User;

import com.twitter.user_service.dto.UserDTO;
import com.twitter.user_service.exceptions.ResourceNotFoundException;
import com.twitter.user_service.model.User;
import com.twitter.user_service.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserDTO getUserById(Long id){

        User user=userRepo.
                findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));

        return modelMapper.map(user,UserDTO.class);
    }
}
