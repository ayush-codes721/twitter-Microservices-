package com.twitter.comment_service.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long id;
    private Long tweetId;


    private Long userId;

    private String content;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;


}
