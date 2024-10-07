package com.twitter.like_service.Clients;

import com.twitter.like_service.DTO.TweetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tweet-service", url = "http://localhost:5011")
public interface TweetClient {

    @GetMapping("/tweet/{tweetId}")
    TweetDTO getTweetById(@PathVariable Long tweetId);
}

