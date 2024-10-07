package com.twitter.follow_service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    @Builder.Default
    private boolean success=true;
    private String message;
}
