package com.twitter.comment_service.DTO;

import lombok.Data;

@Data
public class AddCommentRequestDTO {

    private Long tweetId;


    private String content;
}
