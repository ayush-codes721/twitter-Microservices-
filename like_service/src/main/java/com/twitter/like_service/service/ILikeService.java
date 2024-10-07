package com.twitter.like_service.service;

import com.twitter.like_service.DTO.LikeRequestDTO;
import com.twitter.like_service.DTO.LikeDTO;
import com.twitter.like_service.DTO.MessageResponseDTO;
import com.twitter.like_service.DTO.TweetDTO;
import com.twitter.like_service.response.ApiResponse;

import java.util.List;

public interface ILikeService {

    ApiResponse likeTweet(LikeRequestDTO addLikeRequestDTO);


    MessageResponseDTO unlikeTweet(LikeRequestDTO unlikeRequestDTO);

    Long likeCount(Long tweetId);

    List<TweetDTO> getAllTweetsLikedByUser(Long userId);
}
