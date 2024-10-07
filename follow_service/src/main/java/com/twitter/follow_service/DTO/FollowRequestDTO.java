package com.twitter.follow_service.DTO;

import lombok.Data;

@Data
public class FollowRequestDTO {

    private Long id;

    private Long followerId;

    private Long followingId;
}
