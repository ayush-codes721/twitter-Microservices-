package com.twitter.follow_service.service;

import com.twitter.follow_service.Cilents.UserClient;
import com.twitter.follow_service.DTO.FollowDTO;
import com.twitter.follow_service.DTO.FollowRequestDTO;
import com.twitter.follow_service.DTO.UserDTO;
import com.twitter.follow_service.exception.FollowNotFoundException;
import com.twitter.follow_service.exception.InvalidFollowRequestException;
import com.twitter.follow_service.model.Follow;
import com.twitter.follow_service.repo.FollowRepo;
import com.twitter.follow_service.response.MessageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {

    private final ModelMapper modelMapper;
    private final FollowRepo followRepo;
    private final UserClient userClient;


    @Override
    public FollowDTO followUser(FollowRequestDTO followRequestDTO) {

        if (followRequestDTO.getFollowingId() == null || followRequestDTO.getFollowerId() == null) {
            throw new InvalidFollowRequestException("id is required");
        }

        Long userID = userClient.getUserById(followRequestDTO.getFollowerId()).getId();// it will throw exceptions  is user doesn't exist
        Long follID = userClient.getUserById(followRequestDTO.getFollowingId()).getId();
        if (Objects.equals(userID, follID)) {
            throw new FollowNotFoundException("user can't follow yourself");
        }

        boolean alreadyFollowing = followRepo.existsByFollowerIdAndFollowingId(userID, follID);
        if (alreadyFollowing) {
            throw new InvalidFollowRequestException("You are already following this user");
        }


        Follow follow = modelMapper.map(followRequestDTO, Follow.class);


        Follow savedFollow = followRepo.save(follow);
        return modelMapper.map(savedFollow, FollowDTO.class);
    }

    @Transactional
    @Override
    public MessageResponse unfollowUser(FollowDTO followDTO) {


        boolean exists = followRepo.existsByFollowerIdAndFollowingId(followDTO.getFollowerId(), followDTO.getFollowingId());
        if (!exists) {
            throw new FollowNotFoundException("Follow doesn't exist");
        }


        followRepo.deleteByFollowerIdAndFollowingId(followDTO.getFollowerId(), followDTO.getFollowingId());
        return MessageResponse.builder()
                .message("unfollowed success")
                .build();
    }

    @Override
    public List<UserDTO> getListOfFollowerByUser(Long userID) {


        return followRepo.findFollowerIdsByUserId(userID)
                .stream()
                .map(id -> userClient.getUserById(id))
                .toList();
    }

    @Override
    public List<UserDTO> getListOFFollowingByUser(Long userID) {


        return followRepo.findFollowingIdsByUserId(userID)
                .stream()
                .map(id -> userClient.getUserById(id))
                .toList();
    }
}
