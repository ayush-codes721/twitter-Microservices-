package com.twitter.comment_service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

    @Builder.Default
    private boolean success=false;
    private String message;
}
