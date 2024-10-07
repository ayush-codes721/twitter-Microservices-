package com.twitter.like_service.controller;

import com.twitter.like_service.DTO.*;
import com.twitter.like_service.response.ApiResponse;
import com.twitter.like_service.service.ILikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final ILikeService likeService;

    @Operation(summary = "Like a tweet", description = "Provide userId and tweetId in the request body to like a tweet. no need to provide id ignore that")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tweet liked successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input or user already liked this tweet"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Tweet or User not found")
    })
    @PostMapping("/like")
    public ResponseEntity<ApiResponse> likeTweet(@RequestBody LikeRequestDTO likeRequestDTO) {

        ApiResponse likeResponse = likeService.likeTweet(likeRequestDTO);
        return ResponseEntity.ok(likeResponse);
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<MessageResponseDTO> unlikeTweet(
            @RequestBody LikeRequestDTO unlikeRequestDTO
    ) {
        MessageResponseDTO response = likeService.unlikeTweet(unlikeRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/{tweetId}")
    public ResponseEntity<Long> likeCount(@PathVariable Long tweetId) {
        Long count = likeService.likeCount(tweetId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/user/{userId}/tweets")
    public ResponseEntity<List<TweetDTO>> getAllTweetsLikedByUser(@PathVariable Long userId) {
        List<TweetDTO> likedTweets = likeService.getAllTweetsLikedByUser(userId);
        return ResponseEntity.ok(likedTweets);
    }
}
