package com.twitter.comment_service.repo;

import com.twitter.comment_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    // Count comments by tweetId
    Long countByTweetId(Long tweetId);

    // Retrieve all comments for a specific tweet
    List<Comment> findByTweetId(Long tweetId);

}
