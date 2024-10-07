package com.twitter.like_service.repo;

import com.twitter.like_service.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepo extends JpaRepository<Like,Long> {

    Long countByTweetId(Long tweetId);
    List<Like> findByUserId(Long userId);
    boolean existsByUserIdAndTweetId(Long userId, Long tweetId);
    Optional<Like> findByUserIdAndTweetId(Long userId, Long tweetId);



}
