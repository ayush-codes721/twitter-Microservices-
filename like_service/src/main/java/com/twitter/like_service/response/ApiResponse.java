package com.twitter.like_service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

    @Builder.Default
    private boolean success=true;
    private String message;
    private Object data;
}
