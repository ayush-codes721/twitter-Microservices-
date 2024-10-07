package com.twitter.follow_service.controller;

import com.twitter.follow_service.DTO.FollowDTO;
import com.twitter.follow_service.DTO.FollowRequestDTO;
import com.twitter.follow_service.DTO.UserDTO;
import com.twitter.follow_service.response.MessageResponse;
import com.twitter.follow_service.service.IFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final IFollowService followService;

    // Follow a user
    @PostMapping("/follow")
    public ResponseEntity<FollowDTO> followUser(@RequestBody FollowRequestDTO followRequestDTO) {
        FollowDTO followDTO = followService.followUser(followRequestDTO);
        return new ResponseEntity<>(followDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<MessageResponse> unfollowUser(@RequestBody FollowDTO followDTO) {
        MessageResponse response = followService.unfollowUser(followDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<UserDTO>> getListOfFollowers(@PathVariable Long userId) {
        List<UserDTO> followerIds = followService.getListOfFollowerByUser(userId);
        return ResponseEntity.ok(followerIds);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<UserDTO>> getListOfFollowing(@PathVariable Long userId) {
        List<UserDTO> followingIds = followService.getListOFFollowingByUser(userId);
        return ResponseEntity.ok(followingIds);
    }
}
