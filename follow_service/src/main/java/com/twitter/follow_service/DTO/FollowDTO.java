package com.twitter.follow_service.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FollowDTO {


    private Long id;

    private Long followerId;

    private Long followingId;
}
