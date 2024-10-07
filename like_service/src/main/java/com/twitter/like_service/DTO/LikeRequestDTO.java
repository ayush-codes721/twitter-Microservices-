package com.twitter.like_service.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LikeRequestDTO {

    private Long id;  // Add this field

    private Long userId;

    private Long tweetId;

}
