package com.twitter.like_service.service;

import com.twitter.like_service.Clients.TweetClient;
import com.twitter.like_service.Clients.UserClient;
import com.twitter.like_service.DTO.*;
import com.twitter.like_service.exceptions.LikeNotFoundException;
import com.twitter.like_service.model.Like;
import com.twitter.like_service.repo.LikeRepo;
import com.twitter.like_service.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService {
    private final LikeRepo likeRepo;
    private final ModelMapper modelMapper;
    private final UserClient userClient;
    private final TweetClient tweetClient;

    @Override
    public ApiResponse likeTweet(LikeRequestDTO addLikeRequestDTO) {

        userClient.getUserById(addLikeRequestDTO.getUserId());
        tweetClient.getTweetById(addLikeRequestDTO.getTweetId());

        boolean alreadyLiked = likeRepo.existsByUserIdAndTweetId(addLikeRequestDTO.getUserId(), addLikeRequestDTO.getTweetId());

        if (alreadyLiked) {
            throw new RuntimeException("User has already liked this tweet");
        }


        Like like = modelMapper.map(addLikeRequestDTO, Like.class);


        Like savedLike = likeRepo.save(like);

        LikeDTO likeDTO=modelMapper.map(savedLike, LikeDTO.class);

        return ApiResponse.builder()
                .message("Tweet liked successfully")
                .data(likeDTO)
                .build();
    }


    @Override
    public MessageResponseDTO unlikeTweet(LikeRequestDTO unlikeRequestDTO) {
        if (unlikeRequestDTO.getTweetId() == null || unlikeRequestDTO.getUserId() == null) {
            throw new RuntimeException("Tweet ID and User ID are required");
        }

        userClient.getUserById(unlikeRequestDTO.getUserId());
        tweetClient.getTweetById(unlikeRequestDTO.getTweetId());

        Like existingLike = likeRepo.findByUserIdAndTweetId(
                        unlikeRequestDTO.getUserId(), unlikeRequestDTO.getTweetId())
                .orElseThrow(() -> new RuntimeException("No like found for this user and tweet"));

        likeRepo.delete(existingLike);

        return MessageResponseDTO.builder()
                .message("Unliked successfully")
                .build();
    }

    @Override
    public Long likeCount(Long tweetId) {
        return likeRepo.countByTweetId(tweetId);
    }

    @Override
    public List<TweetDTO> getAllTweetsLikedByUser(Long userId) {
        userClient.getUserById(userId);

        List<Like> likesByUser = likeRepo.findByUserId(userId);

        return likesByUser.stream()
                .map(like -> tweetClient.getTweetById(like.getTweetId()))
                .toList();

    }
}
