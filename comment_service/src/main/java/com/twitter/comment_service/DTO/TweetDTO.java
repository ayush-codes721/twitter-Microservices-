package com.twitter.comment_service.DTO;

import java.time.LocalDateTime;

public class TweetDTO {

    private Long id;

    private Long userId; // Reference to the user who created the tweet

    private String content;


    private LocalDateTime createdAt;
}
