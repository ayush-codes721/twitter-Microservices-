package com.twitter.tweet_service.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class TweetDTO {
    private Long id;

    private Long userId; // Reference to the user who created the tweet

    private String content;


    private LocalDateTime createdAt;
}
