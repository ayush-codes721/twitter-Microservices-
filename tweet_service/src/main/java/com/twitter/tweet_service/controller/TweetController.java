package com.twitter.tweet_service.controller;


import com.twitter.tweet_service.DTO.PostTweetRequestDTO;
import com.twitter.tweet_service.DTO.TweetDTO;
import com.twitter.tweet_service.service.ITweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TweetController {
    private final ITweetService tweetService;

    @PostMapping("/user/create")
    public ResponseEntity<TweetDTO> createTweet(
            @RequestBody PostTweetRequestDTO postTweetRequestDTO,
            @RequestHeader(name = "X-User_Id") Long userId) {

        TweetDTO createdTweet = tweetService.createTweet(postTweetRequestDTO, userId);
        return ResponseEntity.ok(createdTweet);
    }

    // Get a tweet by ID
    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDTO> getTweetById(@PathVariable Long tweetId) {
        TweetDTO tweetDTO = tweetService.getTweetById(tweetId);
        return ResponseEntity.ok(tweetDTO);
    }

    // Get all tweets
    @GetMapping("/tweets")
    public ResponseEntity<List<TweetDTO>> getAllTweets(@RequestHeader(name = "X-User_Id") Long userId) {
        log.info("user id: {}",userId);
        List<TweetDTO> allTweets = tweetService.getAllTweets();
        return ResponseEntity.ok(allTweets);
    }

    // Get all tweets by a specific user
    @GetMapping("/user/tweets")
    public ResponseEntity<List<TweetDTO>> getAllTweetsOfLoggedInUser(@RequestHeader(name = "X-User_Id") Long userId) {
        List<TweetDTO> userTweets = tweetService.getAllTweetByUserId(userId);
        return ResponseEntity.ok(userTweets);
    }

    // Delete a tweet by ID
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long tweetId,@RequestHeader(name = "X-User_Id") Long userId) {
        tweetService.deleteTweet(tweetId,userId);
        return ResponseEntity.noContent().build();
    }
}
