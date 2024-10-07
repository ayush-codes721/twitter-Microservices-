package com.twitter.tweet_service.repo;

import com.twitter.tweet_service.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepo extends JpaRepository<Tweet,Long> {
    List<Tweet> findByUserId(Long userId); // Method to get tweets by user ID

}
