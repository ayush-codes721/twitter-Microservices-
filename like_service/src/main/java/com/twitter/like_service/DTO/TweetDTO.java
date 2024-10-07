package com.twitter.like_service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TweetDTO {

    private Long id;

    private Long userId; // Reference to the user who created the tweet

    private String content;


    private LocalDateTime createdAt;
}
