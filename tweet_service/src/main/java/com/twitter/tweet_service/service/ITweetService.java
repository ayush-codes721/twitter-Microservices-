package com.twitter.tweet_service.service;


import com.twitter.tweet_service.DTO.PostTweetRequestDTO;
import com.twitter.tweet_service.DTO.TweetDTO;

import java.util.List;

public interface ITweetService {
    TweetDTO createTweet(PostTweetRequestDTO postTweetRequestDTO,Long userID);
    TweetDTO getTweetById(Long tweetID);
    List<TweetDTO> getAllTweets();
    List<TweetDTO> getAllTweetByUserId(Long userId);
    void deleteTweet(Long tweetID,Long userId);
}
