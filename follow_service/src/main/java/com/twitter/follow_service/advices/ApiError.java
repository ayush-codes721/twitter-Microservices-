package com.twitter.follow_service.advices;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    @Builder.Default
    private boolean success=false;
    private String error;
    @Builder.Default
    private LocalDateTime timeStamp=LocalDateTime.now();
}
