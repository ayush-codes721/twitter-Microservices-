package com.twitter.tweet_service.service;

import com.twitter.tweet_service.Cilents.UserClient;
import com.twitter.tweet_service.DTO.PostTweetRequestDTO;
import com.twitter.tweet_service.DTO.TweetDTO;
import com.twitter.tweet_service.DTO.UserDTO;
import com.twitter.tweet_service.exceptions.ResourceNotFoundException;
import com.twitter.tweet_service.model.Tweet;
import com.twitter.tweet_service.repo.TweetRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService implements ITweetService {
    private final ModelMapper modelMapper;
    private final TweetRepo tweetRepo;
    private final UserClient userClient;

    @Override
    public TweetDTO createTweet(PostTweetRequestDTO postTweetRequestDTO, Long userID) {

        if (userID == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        UserDTO userDTO = userClient.getUserById(userID);


        Tweet tweet = modelMapper.map(postTweetRequestDTO, Tweet.class);
        tweet.setUserId(userDTO.getId());
        Tweet savedTweet = tweetRepo.save(tweet);
        return modelMapper.map(savedTweet, TweetDTO.class);
    }

    @Override
    public TweetDTO getTweetById(Long tweetID) {

        Tweet tweet = tweetRepo.findById(tweetID).orElseThrow(() -> new ResourceNotFoundException(" tweet not found"));

        return modelMapper.map(tweet, TweetDTO.class);
    }

    @Override
    public List<TweetDTO> getAllTweets() {


        return tweetRepo.findAll()
                .stream()
                .map(tweet -> modelMapper.map(tweet, TweetDTO.class))
                .toList();
    }

    @Override
    public List<TweetDTO> getAllTweetByUserId(Long userId) {

        userClient.getUserById(userId); // Throws an exceptions if the user doesn't exist



        return tweetRepo.findByUserId(userId)
                .stream()
                .map(tweet -> modelMapper.map(tweet,TweetDTO.class))
                .toList();
    }

    @Override
    public void deleteTweet(Long tweetID,Long userId) {

       Tweet tweet= tweetRepo.findById(tweetID).orElseThrow(()->new ResourceNotFoundException("tweet not found"));
       if (!tweet.getUserId().equals(userId)){
           throw new IllegalArgumentException("unauthorized access");
       }
       tweetRepo.delete(tweet);
    }
}
