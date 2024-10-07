package com.twitter.follow_service.service;

import com.twitter.follow_service.DTO.FollowDTO;
import com.twitter.follow_service.DTO.FollowRequestDTO;
import com.twitter.follow_service.DTO.UserDTO;
import com.twitter.follow_service.model.Follow;
import com.twitter.follow_service.response.MessageResponse;
import lombok.Data;

import java.util.List;


public interface IFollowService {

    FollowDTO followUser(FollowRequestDTO followRequestDTO);

    MessageResponse unfollowUser(FollowDTO followDTO);

    List<UserDTO> getListOfFollowerByUser(Long userID);

    List<UserDTO> getListOFFollowingByUser(Long userID);

}
