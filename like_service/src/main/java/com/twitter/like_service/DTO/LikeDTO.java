package com.twitter.like_service.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class LikeDTO {

    @Schema(example = "this is not required don't need to pass")
    private  Long id;

    @Schema(description = "this is a userId pass user id in Long that is present",example = "1")
    private Long userId;

    private Long tweetId;

    private LocalDateTime likedAt;
}
