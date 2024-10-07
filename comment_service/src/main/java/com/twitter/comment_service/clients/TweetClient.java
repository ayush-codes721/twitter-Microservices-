package com.twitter.comment_service.clients;

import com.twitter.comment_service.DTO.TweetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:5011/tweet",name = "tweet-service")

public interface TweetClient {

    @GetMapping("/{tweetId}")
    TweetDTO getTweetById(@PathVariable Long tweetId);
}
